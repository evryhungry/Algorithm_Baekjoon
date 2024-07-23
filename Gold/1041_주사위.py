N = int(input())
nums = list(map(int, input().split()))
sum = 0

if N == 1:
    nums = sorted(nums)
    for i in range(0, 5, 1):
        sum += nums[i]
else:
    nums[0] = min(nums[0], nums[5])
    nums[1] = min(nums[1], nums[4])
    nums[2] = min(nums[2], nums[3])
    nums = sorted(nums[:3])

    sum += nums[0] * (((N-2) ** 2) + 4 * (N-1) * (N-2))
    sum += (nums[0] + nums[1]) * ((N-1) + (N-2)) * 4
    sum += (nums[0] + nums[1] + nums[2]) * 4

print(sum)

