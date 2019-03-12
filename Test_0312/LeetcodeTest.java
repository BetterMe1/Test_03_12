package Test_0312;

/*
55. 跳跃游戏
给定一个非负整数数组，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个位置。
示例 1:
输入: [2,3,1,1,4]
输出: true
解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
示例 2:
输入: [3,2,1,0,4]
输出: false
解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
/*
 * 分析：
 * 指针i从倒数第二位往前遍历，n代表步数，初始为1，
 * 若nums[i]大于或等于n,说明从此位置可以跳到最后一位，并从此位置截断，此位置便为最后一位
 * 若nums[i]小于n,说明从此位置不能跳到最后一位，则n++,向前遍历查看其它位置是否能跳到最后一位，
 * 遍历到最后一位时步数大于1，说明跳跃游戏会失败，返回false,否则返回true.
 */
//public class LeetcodeTest {
//	public static void main(String[] args) {
//		Solution So = new Solution();
//		int[] nums = {2,3,1,1,4};
//		System.out.println(So.canJump(nums));
//	}
//}
//class Solution {
//    public boolean canJump(int[] nums) {
//    	int n = 1;
//    	for(int i=nums.length-2; i>=0; i--){
//    		if(nums[i] >= n){
//    			n=1;
//    		}else{
//    			n++;
//    		}
//    		if(i==0 && n>1){
//    			return false;
//    		}
//    	}
//    	return true;
//    }
//}
/*
45. 跳跃游戏 II
给定一个非负整数数组，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
你的目标是使用最少的跳跃次数到达数组的最后一个位置。
示例:
输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
说明:
假设你总是可以到达数组的最后一个位置。
 */
/*
 * 分析：
 * 要使用最少的跳跃次数到达数组的最后一个位置，那就要在每一步都寻找最优的跳法，最终就会得到全局最优的跳法。
 * 在每一个跳到的位置上先判断能否直接跳到最后一个位置，如果能，那么这一步就是当前最优的，
 * 如果不能，就去寻找下一步的最优位置，找到之后再跳过去。
 * 下一个最优的地方即下一个可以跳最大步数的地方，即下面代码中nums[j]最大。
 */
//public class LeetcodeTest {
//	public static void main(String[] args) {
//		Solution So = new Solution();
//		int[] nums = {2,3,1,1,4};
//		System.out.println(So.jump(nums));
//	}
//}
//class Solution {
//    public int jump(int[] nums) {
//    	int i=0;
//        int res = 0;
//        while(i<nums.length-1){
//        	int steps = nums[i];
//        	//最后一步是个特例，不需要寻找下一个位置，如果能在此时跳到最后一个位置，这一步就是最优的。
//        	if(steps>=nums.length-1-i){
//        		res++;
//        		break;
//        	}
//        	//跳到下一个最优的地方
//        	int max = nums[i+1];//记录最大步数
//        	int index = i+1;//记录索引
//        	for(int j=index; j<=i+steps && j<nums.length-1; j++){
//        		if(nums[j]+j-index >= max){
//        			max = nums[j];
//        			index = j;
//        		}
//        	}
//        	res++;
//        	i = index;
//        }
//        return res;
//    }
//}
/*
905. 按奇偶排序数组
给定一个非负整数数组 A，返回一个由 A 的所有偶数元素组成的数组，后面跟 A 的所有奇数元素。
你可以返回满足此条件的任何数组作为答案。
示例：
输入：[3,1,2,4]
输出：[2,4,3,1]
输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
提示：
1 <= A.length <= 5000
0 <= A[i] <= 5000
 */
public class LeetcodeTest {
	public static void main(String[] args) {
		Solution So = new Solution();
		int[] A = {3,1,2,4};
		int[] res = So.sortArrayByParity2(A);
		for(int i=0; i<res.length; i++){
			System.out.print(res[i] + " ");
		}
	}
}
class Solution {
	//优化前：
	/*
	 * 分析：
	 * k表示排完后偶数的索引，初始为0，指针i遍历数组A，遇到偶数，
	 * 将索引为k到i-1之间的数全部后移一位，索引为i的数放在索引为k的位置上，
	 * 并且k自增1，完毕后返回数组A。
	 * 因为需要大量移动，效率较慢。
	 */
    public int[] sortArrayByParity1(int[] A) {
    	int k = 0;//偶数索引
    	for(int i=0; i<A.length; i++){
    		if(A[i]%2 == 0){
    			int temp = A[i];
    			for(int j=i-1; j>=k; j--){
    				A[j+1] = A[j];
    			}
    			A[k] = temp;
    			k++;
    		}
    	}
    	return A;
    }
   //优化后：
    /*
     * 分析：
     * k表示排完后偶数的索引，初始为0，指针i遍历数组A，遇到偶数，
     * 只需要将索引为k的数与索引为i的数交换，
     * 并且k自增1，完毕后返回数组A。
     * 少量移动，效率较快。
     */
    public int[] sortArrayByParity2(int[] A) {
    	int k = 0;
    	for(int i=0; i<A.length; i++){
    		if(A[i]%2 == 0){
    			int temp = A[i];
    			A[i] = A[k];
    			A[k] = temp;
    			k++;
    		}
    	}
    	return A;
    }
}