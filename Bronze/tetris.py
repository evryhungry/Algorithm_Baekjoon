import curses
import random
import time

# Tetris shapes and their rotations
SHAPES = [
    [['.....',
      '.....',
      '..00.',
      '.00..',
      '.....'],
     ['.....',
      '..0..',
      '..00.',
      '...0.',
      '.....']],
    [['.....',
      '.....',
      '.00..',
      '..00.',
      '.....'],
     ['.....',
      '..0..',
      '.00..',
      '.0...',
      '.....']],
    [['.....',
      '.....',
      '..0..',
      '..0..',
      '..0..',
      '..0..'],
     ['.....',
      '0000.',
      '.....',
      '.....',
      '.....']],
    [['.....',
      '.....',
      '..00.',
      '..00.',
      '.....']],
    [['.....',
      '.....',
      '..0..',
      '.000.',
      '.....'],
     ['.....',
      '..0..',
      '..00.',
      '..0..',
      '.....'],
     ['.....',
      '.....',
      '.000.',
      '..0..',
      '.....'],
     ['.....',
      '..0..',
      '.00..',
      '..0..',
      '.....']],
    [['.....',
      '.....',
      '.00..',
      '..00.',
      '.....'],
     ['.....',
      '..0..',
      '.00..',
      '.0...',
      '.....']]
]

class Tetris:
    def __init__(self, stdscr):
        self.stdscr = stdscr
        self.width = 10
        self.height = 20
        self.board = [[0 for _ in range(self.width)] for _ in range(self.height)]
        self.current_shape = self.new_shape()
        self.next_shape = self.new_shape()
        self.score = 0
        self.level = 1
        self.shape_x = self.width // 2 - 2
        self.shape_y = 0
        self.stdscr.nodelay(1)
        self.stdscr.timeout(1000 // self.level)

    def new_shape(self):
        return random.choice(SHAPES)[0]

    def draw_board(self):
        self.stdscr.clear()
        for y in range(self.height):
            self.stdscr.addstr(y, 0, ' |')
            for x in range(self.width):
                if self.board[y][x]:
                    self.stdscr.addstr(y, x * 2 + 2, '[]')
            self.stdscr.addstr(y, self.width * 2 + 2, '|')
        for y, row in enumerate(self.current_shape):
            for x, cell in enumerate(row):
                if cell == '0':
                    self.stdscr.addstr(y + self.shape_y, (x + self.shape_x) * 2 + 2, '[]')
        self.stdscr.addstr(0, self.width * 2 + 5, f'Score: {self.score}')
        self.stdscr.addstr(1, self.width * 2 + 5, f'Level: {self.level}')
        self.draw_next_shape()
        self.stdscr.refresh()

    def draw_next_shape(self):
        self.stdscr.addstr(3, self.width * 2 + 5, 'Next:')
        for y, row in enumerate(self.next_shape):
            for x, cell in enumerate(row):
                if cell == '0':
                    self.stdscr.addstr(y + 4, (self.width + 1) * 2 + x * 2 + 2, '[]')

    def rotate_shape(self):
        rotated_shape = ["".join(row) for row in zip(*self.current_shape[::-1])]
        if not self.check_collision_with_shape(rotated_shape):
            self.current_shape = rotated_shape

    def move_shape(self, dx, dy):
        self.shape_x += dx
        self.shape_y += dy
        if self.check_collision():
            self.shape_x -= dx
            self.shape_y -= dy

    def drop_shape(self):
        self.shape_y += 1
        if self.check_collision():
            self.shape_y -= 1
            self.lock_shape()
            self.current_shape = self.next_shape
            self.next_shape = self.new_shape()
            self.shape_x = self.width // 2 - 2
            self.shape_y = 0
            if self.check_collision():
                self.game_over()

    def check_collision_with_shape(self, shape):
        for y, row in enumerate(shape):
            for x, cell in enumerate(row):
                if cell == '0':
                    if (x + self.shape_x < 0 or
                        x + self.shape_x >= self.width or
                        y + self.shape_y >= self.height or
                        self.board[y + self.shape_y][x + self.shape_x]):
                        return True
        return False

    def check_collision(self):
        return self.check_collision_with_shape(self.current_shape)

    def lock_shape(self):
        for y, row in enumerate(self.current_shape):
            for x, cell in enumerate(row):
                if cell == '0':
                    self.board[y + self.shape_y][x + self.shape_x] = 1
        self.clear_lines()

    def clear_lines(self):
        new_board = [row for row in self.board if not all(row)]
        lines_cleared = self.height - len(new_board)
        self.board = [[0 for _ in range(self.width)] for _ in range(lines_cleared)] + new_board
        self.score += lines_cleared * 100
        if lines_cleared > 0:
            self.level += 1
            self.stdscr.timeout(1000 // self.level)

    def game_over(self):
        self.stdscr.addstr(self.height // 2, self.width * 2 // 2 - 4, "Game Over")
        self.stdscr.refresh()
        time.sleep(2)
        self.stdscr.nodelay(0)
        self.stdscr.getch()
        exit()

    def main(self):
        self.shape_x = self.width // 2 - 2
        self.shape_y = 0
        self.current_shape = self.new_shape()
        self.next_shape = self.new_shape()
        self.draw_board()

        while True:
            self.drop_shape()
            self.draw_board()
            key = self.stdscr.getch()
            if key == curses.KEY_LEFT:
                self.move_shape(-1, 0)
            elif key == curses.KEY_RIGHT:
                self.move_shape(1, 0)
            elif key == curses.KEY_DOWN:
                self.drop_shape()
            elif key == curses.KEY_UP:
                self.rotate_shape()
            elif key == ord('q'):
                break
            self.stdscr.refresh()
            time.sleep(0.1)

def main(stdscr):
    game = Tetris(stdscr)
    game.main()

if __name__ == "__main__":
    try:
        curses.wrapper(main)
    except curses.error:
        print("Curses error: setupterm: could not find terminal")
