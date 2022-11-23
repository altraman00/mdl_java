package algorithm.day20221123;

/**
 * 二分查找法
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/binary-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : kun
 * @since : 2022年11月23日 10:33
 * ----------------- ----------------- -----------------
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 6;
        int search = search(nums, target);
        System.out.println(search);
    }


    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int midVal = nums[mid];
            System.out.println("left=" + left + " right=" + right + " mid=" + mid + " midVal=" + midVal);

            if (target == midVal) {
                result = mid;
                break;
            } else if (target < midVal) {
                right = mid - 1;
            } else if (target > midVal) {
                left = mid + 1;
            }
        }
        return result;
    }

}
