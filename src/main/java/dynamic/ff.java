package dynamic;

public class ff {
    4.动态规划
1.连续子数组和最大值
    输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

    要求时间复杂度为O(n)。

             示例1:

    输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
    输出: 6
    解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
    关键划分子问题，并且各阶段子问题是有序的，以每个元素作为连续数组的最尾巴元素划分阶段，则每个阶段的发展情况用dp[i]存储，即为该阶段中的连续数组最大和的值，则两个阶段之间的状态关系可以得出来。
            1.状态定义： 设动态规划列表 dpdp ，dp[i]dp[i] 代表以元素 nums[i]nums[i] 为结尾的连续子数组最大和。

    为何定义最大和 dp[i]dp[i] 中必须包含元素 nums[i]nums[i] ：保证 dp[i]dp[i] 递推到 dp[i+1]dp[i+1] 的正确性；如果不包含 nums[i]nums[i] ，递推时则不满足题目的 连续子数组 要求。
            2.转移方程： 若 dp[i-1] \leq 0dp[i−1]≤0 ，说明 dp[i - 1]dp[i−1] 对 dp[i]dp[i] 产生负贡献，即 dp[i-1] + nums[i]dp[i−1]+nums[i] 还不如 nums[i]nums[i] 本身大。

    当 dp[i - 1] > 0dp[i−1]>0 时：执行 dp[i] = dp[i-1] + nums[i]dp[i]=dp[i−1]+nums[i] ；
    当 dp[i - 1] \leq 0dp[i−1]≤0 时：执行 dp[i] = nums[i]dp[i]=nums[i] ；
            3.初始状态： dp[0] = nums[0]dp[0]=nums[0]，即以 nums[0]nums[0] 结尾的连续子数组最大和为 nums[0]nums[0] 。
            4.返回数组中的最大值。

    class Solution {
    public int maxSubArray(int[] nums) {
       int[] dp=new int[nums.length];
       int max=nums[0];
       dp[0]=nums[0];
       for(int i=1;i<nums.length;i++){
         dp[i]=dp[i-1]>0?dp[i-1]+nums[i]:nums[i];//只有前面的能够作正的贡献的时候才加上，否则只能从新开始。不能单独以nums[i]来为界，因为可能只要加入一个负数也能够形成最大和
           a=Math.max(a+nums[i],nums[i]);//a就是以当前元素为结尾的时候所形成的最大的连续子数组和。每一个
          max=Math.max(max,a);
          max=Math.max(dp[i],max);
       }
       return max;
    }
    }
（再看,如果不能重复呢？）2.给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。

    示例:

    nums = [1, 2, 3]
    target = 4

    所有可能的组合为：
            (1, 1, 1, 1)
            (1, 1, 2)
            (1, 2, 1)
            (1, 3)
            (2, 1, 1)
            (2, 2)
            (3, 1)

    请注意，顺序不同的序列被视作不同的组合。

    class Solution {
    public int combinationSum4(int[] nums, int target) {
//根据 题意定义状态表示，即创建一个数组来存储每个阶段为I时后的和为i的组合数目。    
  int[] dp=new int[target+1];  //dp[i]表示组合和为i的时候的组合个数。
     dp[0]=1;//设置数组边界，即定义该
 for(int i=1;i<target+1;i++){
      for(int num:nums){
     if(num<=i){dp[i]+=dp[i-num];
     //对于i前面得索引w若有w=i-num，即i=w+num，所以w索引出的组合数目就是对于一个num加入后求和就是会等于i，这样其对应的组合数目就是dp[w]中的组合数目，同理其它num的也可以得到。
       } 
    }
            }
            return dp[target];
        }
    }

（再看）3.一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。

    一道菜的「喜爱时间」系数：定义为烹饪这道菜以及之前每道菜所花费的时间乘以这道菜的满意程度，也就是time[i]*satisfaction[i]。

    请你返回做完所有菜「喜爱时间」总和的最大值为多少。

    你可以按 任意 顺序，安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。

    使用贪心算法，在每个阶段选取最优的解。
    class Solution {
    public int maxSatisfaction(int[] satisfaction) {
         Arrays.sort(satisfaction);//满意程度可能是负的
         int len=satisfaction.length;
         int sum=0;//贪心算法的是否选取的规则。
         int ans=0;//记录最大值喜爱系数。
         for(int i=len-1;i>=0;i--){//从满意程度最大的开始。
            if(sum+satisfaction[i]>0){
                 sum+=satisfaction[i];
                 ans+=sum;//将乘法转化成了加法了。
            }else{
             break;
            }
            }
         return ans;
    }
    }
4.写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：

    F(0) = 0,   F(1) = 1
    F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
    斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
    解：1.划分子问题：对于任意一个n，都是其前两项的和n-1和n-2两项，因此可以求解每一个阶段的即每一项的值sum[i].
            3.状态变量定义为：sum[i]表示第i项的斐波那契数列的值。
            4.状态转移方程为：sum[i]=sum[i-1]+sum[i-2].
            5.状态变量的初始值为：sum[o]=0,sum[1]=1.
    class Solution {
    public int fib(int n) {
      if(n<=1) return n;
      int[] sum=new int[2];//因为每一项都是前两项的和，所以只要保存前面两项值即可，相加后的值保存在sum[1],原来的sum[1]往前面挪一位。
      sum[0]=0;
      sum[1]=1;
      int a=0;
      for(int i=2;i<=n;i++){
         a=sum[1];
         sum[1]=a+sum[0];
         sum[0]=a;
         sum[1]%=1000000007;
      }
      return sum[1];
    }
        5.一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
        答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
        解：对于没跳一级台阶都相当于回到原点一样，还要决定是跳两级还是一级台阶，相当于一颗二叉树一样循环跳台阶，并且每条跳上一级或者两级台阶，一级时：则剩余n-1个台阶。f(n)=f(n-1)+f(n-2),直接用递归求解时则要计算很多重复的。所以可以采用动态规划，并且进行优化。取状态变量dp[i]表示为跳上i级台阶的调法数目。
        Dp[i]=dp[i-1]+dp[i-2]
        class Solution {
    public int numWays(int n) {
   if(n==0) return 1;
   if(n<=2) return n;
   int[] sum=new int[2];
   sum[0]=1;//当n=1的时候
   sum[1]=2;//当n=2的时候的跳法数目。
   int a=0;
   for(int i=3;i<=n;i++){
     a=sum[1];
     sum[1]=sum[0]+a;
     sum[0]=a;
     sum[1]%=1000000007;
   }
   return sum[1];
    }
        }
（第三遍）6.请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。

        示例 1:

        输入:
        s = "aa"
        p = "a"
        输出: false
        解释: "a" 无法匹配 "aa" 整个字符串。
        解题思路

        假设主串为 A，模式串为 B 从最后一步出发，需要关注最后进来的字符。假设 A 的长度为 n ，B 的长度为 m ，关注正则表达式 B 的最后一个字符是谁，它有三种可能，正常字符、*∗ 和 .（点），那针对这三种情况讨论即可，如下：

        如果 B 的最后一个字符是正常字符，那就是看 A[n-1] 是否等于 B[m-1]，相等则看 A_{0..n-2}A 0..n−2与 B_{0..m-2}B0..m−2 ，不等则是不能匹配，这就是子问题。

        如果 B 的最后一个字符是.，它能匹配任意字符，直接看 A_{0..n-2}A
        0..n−2与 B_{0..m-2}B 0..m−2
​
        如果 B 的最后一个字符是*它代表 B[m-2]=c 可以重复0次或多次，它们是一个整体 c*

        情况一：A[n-1] 是 0个 c，B最后两个字符废了，能否匹配取决于 A 0..n−1和 B 0..m−3是否匹配
        情况二：A[n-1] 是多个 c 中的最后一个（这种情况必须 A[n-1]=c或者 c='.'），所以 A 匹配完往前挪一个，B继续匹配，因为可以匹配多个，继续看 A_{0..n-2}A 0..n−2和 B_{0..m-1}B 0..m−1是否匹配。

        转移方程
                两个字符要匹配肯定是

        f[i][j]代表 A 的前 i 个和 B 的前 j 个能否匹配

        1.对于前面两个情况，可以合并成一种情况 f[i][j] = f[i-1][j-1]f[i][j]=f[i−1][j−1]

        2.对于第三种情况，对于 c* 分为看和不看两种情况

        不看：直接砍掉正则串的后面两个， f[i][j] = f[i][j-2]f[i][j]=f[i][j−2]
        看：正则串不动，主串前移一个，f[i][j] = f[i-1][j]f[i][j]=f[i−1][j]
        初始条件

        特判：需要考虑空串空正则

        1.空串和空正则是匹配的，f[0][0] = truef[0][0]=true
        2.空串和非空正则，不能直接定义 true 和 false，必须要计算出来。（比如A=A= '' '' ,B=a*b*c*）
        3.非空串和空正则必不匹配，f[1][0]=...=f[n][0]=false
        4.非空串和非空正则，那肯定是需要计算的了。
        大体上可以分为空正则和非空正则两种，空正则也是比较好处理的，对非空正则我们肯定需要计算，非空正则的三种情况，前面两种可以合并到一起讨论，第三种情况是单独一种，那么也就是分为当前位置是*和不是*两种情况了。

        结果
        解法1：动态规划
        我们开数组要开 n+1n+1 ，这样对于空串的处理十分方便。结果就是 f[n][m]

        class Solution {
    public boolean isMatch(String A, String B) {
        int n = A.length();
        int m = B.length();
        boolean[][] f = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                //分成空正则和非空正则两种
                if (j == 0) {//当为空正则的时候，j是只有一个的，所以就要看s字符串是否也为空了，是空（i也一直是0），则就也是true，否则就一直都是false下去。
                    f[i][j] = i == 0;
                } else {
                    //非空正则分为两种情况 * 和 非*
                    if (B.charAt(j - 1) != '*') {
                        if (i > 0 && (A.charAt(i - 1) == B.charAt(j - 1) || B.charAt(j - 1) == '.')) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    } else {
                        //碰到 * 了，分为看和不看两种情况
                     //不看
                        if (j >= 2) {
                            f[i][j] |= f[i][j - 2];
                        }
                        //看，A[n-1] 是多个 c 中的最后一个（这种情况必须 A[n-1]=c或者 c='.'），所以 A匹配完往前挪一个，B 继续匹配
                        if (i >= 1 && j >= 2 && (A.charAt(i - 1) == B.charAt(j - 2) || B.charAt(j - 2) == '.')) {
                            f[i][j] |= f[i - 1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }
        }
        解法2:动态规划
        dp[i][j]代表 A 的前 i 个和 B 的前 j 个能否匹配，为什么要这样匹配呢，因为对于主串A而言，可能更少个数的字符就能和更多字符的B匹配完成。
        状态定义： 设动态规划矩阵 dp ， dp[i][j] 代表字符串 s 的前 i 个字符和 p 的前 j 个字符能否匹配。

        1.转移方程： 需要注意，由于 dp[0][0] 代表的是空字符的状态（所以dp里面的索引比实际的更大一点。）， 因此 dp[i][j] 对应的添加字符是 s[i - 1] 和 p[j - 1] 。（dp里面从第1行和第1列开始才算代表字符的匹配添加）

        2.当 p[j - 1] = '*' 时， dp[i][j] 在当以下任一情况为 true时等于 true ：
（其实就是看*要代替其前面的字符几次，0，1，或者增加1个）

        dp[i][j - 2]： 即将字符组合 p[j-2]*看作出现 0 次时，即从后往前看，s的前i-1个字符是否能和p的前i-3个的字符（因为j-2位置的字符只出现0次看作）能否匹配；
        dp[i][j - 1]： 即将字符组合 p[j - 2] * 看作出现 1 次时，即*看作出现0次，能否匹配；
        dp[i - 1][j] 且 s[i - 1] = p[j - 2]: 即让字符 p[j - 2] 多出现 1 次时，那么i-1的字符要和j-2即多出现的字符一样，并且i-2之前的字符和j-1也是匹配的（相当于*让【j-2】处的字符出现1次。）；
        dp[i - 1][j] 且 p[j - 2] = '.': 即让字符 '.' 多出现 1 次时，能否匹配；

        3.当 p[j - 1] != '*' 时， dp[i][j] 在当以下任一情况为 true 时等于 true ：

        dp[i - 1][j - 1] 且 s[i - 1] = p[j - 1]： 即让字符 p[j - 1] 多出现一次时，能否匹配；
        dp[i - 1][j - 1] 且 p[j - 1] = '.'： 即将字符 . 看作字符 s[i - 1] 时，能否匹配；

        4.初始化： 需要先初始化 dp 矩阵首行，以避免状态转移时索引越界。
        dp[0][0] = true： 代表两个空字符串能够匹配。
        dp[0][j] = dp[0][j - 2] 且 p[j - 1] = '*'： 首行 s 为空字符串，因此当 p 的偶数位为 * 时才能够匹配（即让 p 的奇数位出现 0 次，保持 p 是空字符串）。因此，循环遍历字符串 p ，步长为 2（即只看偶数位）。
        class Solution {
    public boolean isMatch(String s, String p) {
       int n=s.length()+1;
       int m=p.length()+1;
       boolean[][] dp=new boolean[n][m];//因为是有空字符串的情况的，所以dp必然是要更长的。
       dp[0][0]=true;//空串对空串。
       for(int j=2;j<m;j+=2){
//s空串和p进行匹配。
           //当s为空串的时候执行。
          //当p为空串的时候，对应两者为null的时候即返回dp[0][0],否则就不匹配直接是false，*前一定要有字符才能代表前面的0次到n次，捆绑为一体。
           dp[0][j]=(dp[0][j-2]&&(p.charAt(j-1)=='*'));//即只有j-3之前的字符是为空匹配的即可，所只有当*出现在偶数位的时候才能匹配s的空，即让j-2位置的字符抵消不出现。
       }
       for(int i=1;i<n;i++){//s不为空串的时候
           for(int j=1;j<m;j++){//因为s都是正常字符的主串，所以只分p末尾遇到的是否是*即可。
               1.if(p.charAt(j-1)=='*'){
                if(dp[i][j-2]) dp[i][j]=true;//p[j-2]*出现0次，即*抵消p[j-2]，那么只要j-3的字符和i-1的字符是匹配的就为true。
                else if(dp[i][j-1]) dp[i][j]=true;//当p[j-2]*出现1次，即*不起作用，那么只要j-2的字符和i-1的字符匹配就dp[i][j-1]是为true
                else if(dp[i-1][j]&&(s.charAt(i-1)==p.charAt(j-2))) dp[i][j]=true;//当p[j-2]*出现两次，即*补充一位的时候，那么久看j-2之前是否能和i-1之前的字符匹配，并且i-1的字符要等于j-2的。
                else if( dp[i-1][j]&&p.charAt(j-2)=='.' ) dp[i][j]=true;//和上面是同理的，只是如果p的j-2处为.的话也可以和s的i-1的字符匹配。
               2.}else{
                if(dp[i-1][j-1]&&(s.charAt(i-1)==p.charAt(j-1))) dp[i][j]=true;//当不是以*结尾的时候，那么要判断i-1和j-1之前的字符是否匹配，直接看dp[i-1][j-1]即i-2和j-2之前的字符是否匹配并且i-1和j-1的字符是否匹配即可。
                else if(dp[i-1][j-1]&&(p.charAt(j-1)=='.')) dp[i][j]=true;//和前面类似，只是这里是j-1处为.，它可以s中i-1处的任意字符
               }
           }
       }
       return dp[n-1][m-1];

    }    
        }



（第三遍，要深刻理解）7. 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
        示例 1:
        输入: "abcabcbb"
        输出: 3
        解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        示例 2:
        输入: "bbbbb"
        输出: 1
        解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
        示例 3:
        输入: "pwwkew"
        输出: 3
        解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
        解法一：暴力递归（从前向后考虑，即每一个位置及之后的最长不重复子字符串（是串不是序列），时间复杂度为O（N^2））
        class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s==null) return 0;
         if(s.equals("")) return 0;
        else {
           Map<Character,Integer> norepatechar=new HashMap();
           return maxchar(s,0,norepatechar);
        }
            }
    public int maxchar(String s,int index,Map<Character,Integer> norepatechar){//返回自index 位置处及之后的的最长字符串数目。
         if(index>=s.length()-1) return 1;
         int maxlengths=0;
        norepatechar.clear();
        for(int i=index;i<s.length();i++){
            if(norepatechar.containsKey(s.charAt(i))&&(norepatechar.get(s.charAt(i))>=index)) break;
            norepatechar.put(s.charAt(i),i);//保存在字符串数组中的，各字符串的最后面的位置，因为前面的会被覆盖掉。
            maxlengths++;
        }
         return Math.max(maxchar(s,index+1,norepatechar),maxlengths);//将当前最长的字符串数目和index+1处的进行比较，更大的返回。
            }
        }
        解法二：动态规划，从后向前考虑，即某个位置为结尾的字符串中的最长的子符串。

        class Solution {
    public int lengthOfLongestSubstring(String s) {
 Map<Character, Integer> dic = new HashMap<>();//从前向后，存储某个位置字符的之前遍历过从字符串中最近的位置。
        int res = 0, tmp = 0（tmp是保存了上一个位置的最大的子字符串数目。）;
        for(int j = 0; j < s.length(); j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); // 获取当前位置字符在之前遍历过的字符串中的最右边的索引 i，若没有则直接返回-1.
            dic.put(s.charAt(j),j); // 更新哈希表
            tmp = tmp < j - i ?  tmp + 1 : j - i; // dp[j - 1] -> dp[j]，当前位置的最大的子字符串数目。（j-i的位置之间可能已经存在之前相同的字符了，就是之前已经结束的，所以这个时候计算该位置之前的饿字符串最长就不能带i位置了，
                    1.即就是上一个位置的最长字符串数目是要大于j-i则表示此j-i之间没有相同的字符了。	2.否则上一个的最长字符串长度都是小于j-i的，则之间肯定已经存在重复的了，所以就不能算，则最长的就是tmp+1了）
//关键之处。要理解透，一直连续的话就base加的去，当断了的话，即出现不连续的了，就会对tmp重新赋值了。
                                      //当i-j更小或者相等了的话，说明断了连续，重新对tmp赋值了。
                                      //tmp更小的话，说明正处于当前的连续当中。

            res = Math.max(res, tmp); // max(dp[j-1],dp[j])
        }
        return res;//保存的是index位置之前的字符串中最长的子字符串的字符数目。
            }
            解法三：双指针
（第三遍，没有马上就有思路，特别是动态规划）8.我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

 

            示例:

            输入: n = 10
            输出: 12
            解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。

            解法一：小根堆
            class Solution {
    public int nthUglyNumber(int n) {
       Set<Long> nugly=new HashSet();
       PriorityQueue<Long> numbers=new PriorityQueue<Long>();
       numbers.add(1l);
       nugly.add(1l);
       int count=0;
       long nmax=0;//因为到后面丑数将会非常的大，long是8位字节，int则是4字节。
       while(count<n){//每次都弹出最小的一个元素，同时根据丑数的定义，新加入丑数，并且使用set进行去重。
           nmax=(long)numbers.poll();
           count++;
            if(nugly.add(nmax*2)){
                numbers.add(nmax*2);
            }
             if(nugly.add(nmax*3)){
                numbers.add(nmax*3);
            }
             if(nugly.add(nmax*5)){
                numbers.add(nmax*5);
            }
       }
    return (int)nmax;
    }
            }

            解法二：动态规划
            Xn+1是当前要求的丑数，它肯定是前面某个已经求出来符合要求的元素乘以2或者3或者5里面的最小值。Xn则是最接近Xn+1的丑数。分别独立判断 dp[i] 和 dp[a]× 2dp[a]×2 , dp[b]×3 , dp[c]×5 的大小关系，若相等则将对应索引 a , b , c 加 1 。 丑数可以拆分为几个小丑数 的性质 来得到的。这样每次取到最接近的丑数，对应的因子就 +1，就能保证取到所有的丑数。


            class Solution {
    public int nthUglyNumber(int n) {
    int[] dp=new int[n];
    dp[0]=1;
    int a=0;
    int b=0;
    int c=0;
    for(int i=1;i<n;i++){
        dp[i]=Math.min(Math.min(2*dp[a],3*dp[b]),5*dp[c]);
        if(dp[i]==2*dp[a]) a++;//其实就是当2*dp[a]已经出现的了话，因为前面的每一个元素都需要乘上2的，这个位置的已经乘过了，就不用在乘了，那就说名此时dp[a]就不用再乘2了，需要索引加去计算比较。
        if(dp[i]==3*dp[b]) b++;
        if(dp[i]==5*dp[c]) c++;
    }
    return dp[n-1];
    }
            }
            解法三：优先队列
            9.在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

 

            示例 1:

            输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
            输出: 12
            解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物

            解法一：递归
            class Solution {
    public int maxValue(int[][] grid) {
        if(grid==null||grid.length==0) return 0;
            return findmaxvalue(grid,0,0);
    }
    public int finmaxvalue(int[][] grid,int row,int line){//在grid[row][line]位置时到右下角位置的最远路径长度。
        if(row==grid.length-1&&line==grid[0].length-1) return grid[row][line];
        int rightmax=0;
        int upmax=0;
        if(line<grid[0].length-1){
            rightmax=finmaxvalue(grid,row,line+1);
        } 
        if(row<grid.length-1){
           upmax=finmaxvalue(grid,row+1,line);
        }
        return Math.max(rightmax,upmax)+grid[row][line];
    }
            }

            解法二：动态规划
            class Solution {
    public int maxValue(int[][] grid) {
        if(grid==null||grid.length==0) return 0;
       int row=grid.length;
       int line=grid[0].length;
       int[][] dp=new int[row][line];//dp[i][j]表示在i，j位置到右下角的最长路径的长度。
       dp[row-1][line-1]=grid[row-1][line-1];
       for(int i=row-2;i>=0;i--){//最后一列，进行初始化，只能是往下走的。
           dp[i][line-1]=grid[i][line-1]+dp[i+1][line-1];
       }
       for(int j=line-2;j>=0;j--){//最后一行初始化，只能往右边走。
           dp[row-1][j]=dp[row-1][j+1]+grid[row-1][j];
       }
       for(int i=row-2;i>-1;i--){//一行行进行状态转移，只能往下或者右边走，所以要比较dp中的右和下位置的大小。
           for(int j=line-2;j>-1;j--){
               dp[i][j]=grid[i][j]+Math.max(dp[i+1][j],dp[i][j+1]);
           }
       }
       return dp[0][0];
    }
    public int finmaxvalue(int[][] grid,int row,int line){//在grid[row][line]位置时到右下角位置的最远路径长度。
        if(row==grid.length-1&&line==grid[0].length-1) return grid[row][line];
         
        int rightmax=0;
        int upmax=0;
        if(line<grid[0].length-1){
            rightmax=finmaxvalue(grid,row,line+1);
        } 
        if(row<grid.length-1){
           upmax=finmaxvalue(grid,row+1,line);
        }
        return Math.max(rightmax,upmax)+grid[row][line];
    }
            }

            10.假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

 

            示例 1:

            输入: [7,1,5,3,6,4]
            输出: 5
            解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
            注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

            解法1：暴力解法
            class Solution {
    public int maxProfit(int[] prices) {
            //数组中后面减前面的差值最大的一组
            //暴力解法
            int len=prices.length;
            int max=0;
            for(int i=0;i<prices.length;i++){
                for(int j=i+1;j<prices.length;j++){
                    max=(prices[j]-prices[i])>max? prices[j]-prices[i]:max;
                }
            }
            return max;}}

            解法2：从左到右的递归
            class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length==0) return 0;
        return find(prices,prices.length-1)[0];
    }
    public int[] find(int[] prices,int endindex){//某以endindex为结尾的数组中的最大的利润数目及最小值
        if(endindex==0) return new int[]{0,prices[0]};
 //如何得到当层递归的最大利润数及最小值。
        int[] save=find(prices,endindex-1);
        int  indexsub1max=save[0];
        int sub1min=save[1];
        if(indexsub1max<prices[endindex]) indexsub1max=(indexsub1max>prices[endindex]-sub1min)?indexsub1max:prices[endindex]-sub1min;//当前一个数组中的最大利润数小于prices[endindex]值的时候才有更新最大值的必要，否则就是它本身。
        sub1min=sub1min<prices[endindex]? sub1min:prices[endindex];//更新最小值。
       return new int[]{indexsub1max,sub1min};}}
            解法3：动态规划（不用改都可以，因为上面是没有重复调用的时间复杂度就是O（n））
            用一个同长度的数组dp[],dp[i]表示的是以i结尾的数组的最大利润，同时使用一个min变量保存当前数组中的最小值。只是动态规划可以减少空间复杂度，只需要利用两个普通变量就可以。
            11.把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 

            你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

 

            示例 1:

            输入: 1
            输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
            解法1：动态规划（熟悉概率论的二项式定律）
            class Solution {
    public double[] twoSum(int n) {
        double[] pre={1/6d,1/6d,1/6d,1/6d,1/6d,1/6d};//用pre数组保存前面n-1个筛子的可能出现值的概率。
        for(int i=2;i<=n;i++){
            double[] tmp=new double[i*5+1];//当有i个筛子的时候的各值出现的概率存储数组。6*n-n+1个数值，最大最小值之间是连续的。
            for(int j=0;j<pre.length;j++){//针对于原来的数组，每增加一个筛子，原先数组中的一个最小值就要被去除掉，并且增加一定的长度，原先数组的存储的概率也需要改变，后面增加的值的概率也和前面有关系。
                for(int x=0;x<6;x++)//pre数组中的每一个数值，在增加一个筛子的时候，都能贡献到后六位（含本身），1，2，3，4，5，6.
                tmp[j+x]+=pre[j]/6;//原来pre数组中存储的概率由于一个筛子的增加也要重新更新。其实就是二项式定律。原数组：[1,2,3,4,5,6]-后面数组：[2,3,4,5,6,7,8,9,10,11,12] //如对于3，原先1值会贡献一次，2也会贡献一次，所以是累加起来，每6位。
            }
            pre=tmp;   
        }
        return pre;
    }
            }

            解法2：递归

（第三边，就是删除元素的具体位置）12.0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。

            例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
            解法1：递归
            n个数字的圆圈，不断删除第m个数字，我们把最后剩下的数字记为f(n,m)：有n个数字的时候，不断删除第m个后剩下的数字。
            class Solution {
    public int lastRemaining(int n, int m) {
       //数组暴力解法。
        /*int res=0;
        boolean[] mark=new boolean[n];
        for(int i=1;i<n;i++){//删除n-1个数。
            int w=0;//标记没有被删除的数。
            while(w!=m){//当标记数目不等于m的时候，继续，否则退出。
              if(!mark[res]) w++;//当mark[res]为false的时候，则没有被删除w++
              if(w!=m){
               if(res==n-1) res=0;//当索引res移动到n-1的时候，从0继续开始。
               else res++;//否则直接++
              }
            }
            mark[res]=true;//找到第m个要删除的数，进行删除。
        }
        int x=0;
        while(mark[x]) x++;//找到最后一个没有被删除的数，为false的下标。
        return x;*/
        //hashmap
       if(n < 1 || m < 1)       
        return -1;
       if(n == 1)//只有一个数字的时候，当然就返回1了。
         return 0;
       return (lastRemaining(n-1, m) + m) % n;//通过公式可以由第n-1个剩下的数字，求出n个的时候剩下的数字。
                }
            }
            解法3：链表模拟
            class Solution {
  public int lastRemaining(int n, int m) {
     if(n==0||m==0)
        return -1;
     List<Integer> list=new ArrayList<>();//先将所有的数字加入到list保存顺序的list里面。
     for(int i=0;i<n;i++)
        list.add(i);
     int c=(m-1)%n;//举例可以得出第一次删除的数字下标为(m-1)%n记为c，之后每一次删除的数字下标均为(c+m-1)%list.size()，取余的原因是m可能比n大
     while(list.size()!=1) {
        list.remove(c);
        c=(c+m-1)%list.size();  
     }
     return list.get(0);
 }
            }
            解法2：动态规划
（类似背包问题，三维背包问题，初始化要注意了）13.给你一个二进制字符串数组 strs 和两个整数 m 和 n 。

            请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。

            如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。

 

            示例 1：

            输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
            输出：4
            解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
            其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
            解法一：暴力递归法和记忆化搜索
            class Solution {
    public int[][][] memo;
    public int findMaxForm(String[] strs, int m, int n) {
        //从右到左的模型，返回数组i之前的最大的子集的数目。
        if(strs.length==0||strs==null) return 0;
          /*return findmax(strs,m,n,strs.length-1);*/
        this.memo=new int[strs.length][m+1][n+1];//因为对于0，1，2...m就是有m+1个要存储的。
        for(int i = 0;i<memo.length;i++){
        for(int j=0;j<memo[i].length;j++){
            Arrays.fill(memo[i][j],-1); //三维矩阵的添加元素，一竖一竖的添加元素
        }
       
    }
     return findmax(strs,memo,m,n,strs.length-1);
    }
    public int findmax(String[] strs, int m, int n,int end){//返回字符串数组中以[0,...,end]之间的最大的子集。
        if(end<0) return 0;
        String cur=strs[end];
        int numbers0=0;
        int numbers1=0;
        for(int i=0;i<cur.length();i++){
            if(cur.charAt(i)=='0') numbers0++;
            else numbers1++;
        }
        //只要当有任何一个0，1超出了m,n的大小，则直接进入到end-1出寻找最大。
        //都是小于的话，则对于end-1的位置就可能有两种情况了，1.直接进入end-1,m,n看最大子集个数，即不看end处的字符串。 2.1+(end,m-numbers1,n-numbers0):即当end处的字符串要算一个构成的话就是加个1+end-1处m-numbers1,n-numbers0的最大子集。（就是选择当前end的情况的最大子集个数和不选择当前end字符串的最大子集个数。）
        if(n>=numbers1&&m>=numbers0){
            return Math.max(findmax(strs,m,n,end-1),1+findmax(strs,m-numbers0,n-numbers1,end-1));//这一步非常关键，即对于一个位置处的数组的最大子集数目，首先看它本身的字符的0，1的个数，1.当本身就超了的话，只能是看前面一个的了end-1的最大子集数目了；2.当本身不超的话，就要看前面的end-1处的数组选择了当前end的字符串，则对应的1+end-1处的最大子集数目或者不选择该字符串的时候的最大子集数目。
        }else{
            return findmax(strs,m,n,end-1);//不要当前的。
        }
    }
     public int findmax(String[] strs,int[][][] save1 ,int m, int n,int end){//把每一个递归都是首先检查有没有计算过，计算过则直接返回，否则继续计算当前递归的最大子集数目，计算出来后先存储，然后再返回。
         if(end<0) return 0;//执行到这里的end是不会再执行后面的递归的了，即不会再存储到save1数组中了。
         if(save1[end][m][n]!=-1) return save1[end][m][n];
         String cur=strs[end];
        int numbers0=0;
        int numbers1=0;
        for(int i=0;i<cur.length();i++){
            if(cur.charAt(i)=='0') numbers0++;
            else numbers1++;
        }
        //只要当有任何一个0，1超出了m,n的大小，则直接进入到end-1出寻找最大。
        //都是小于的话，则对于end-1的位置就可能有两种情况了，1.直接进入end-1,m,n看最大子集个数。 2.1+(end,m-numbers1,n-numbers0):即当end处的字符串要算一个构成的话就是加个1+end-1处m-numbers1,n-numbers0的最大子集。（就是选择当前end的情况的最大子集个数和不选择当前end字符串的最大子集个数。）
        if(n>=numbers1&&m>=numbers0){
          save1[end][m][n]=Math.max(findmax(strs,save1,m,n,end-1),1+findmax(strs,save1,m-numbers0,n-numbers1,end-1));
        }else{
            save1[end][m][n]=findmax(strs,save1,m,n,end-1);
        }
        return save1[end][m][n];//其它递归的出口。
     }
            }
            解法2：动态规划（这里要明白对于i位置处的时候，（m,n）能够出现不同的值（实际是两个），因为当运行到后面一个位置的时候，后面位置的字符串可能是取用或者不取用。）
            class Solution {
    public int[][][] memo;
    public int findMaxForm(String[] strs, int m, int n) {
        //从右到左的模型，返回数组i之前的最大的子集的数目。
        if(strs.length==0||strs==null) return 0;
        /*return findmax(strs,m,n,strs.length-1);*/
        /*this.memo=new int[strs.length][m+1][n+1];//因为对于0，1，2...m就是有m+1个要存储的。*/
        /*for(int i = 0;i<memo.length;i++){
        for(int j=0;j<memo[i].length;j++){
            Arrays.fill(memo[i][j],-1);//三维矩阵的添加元素。
        }*/
        int[][][] dp=new int[strs.length][m+1][n+1];//三维数组进行递归了。
       for(int i=0;i<strs.length;i++){//字符串是从0位置开始的。
          String cur=strs[i];
          int numbers0=0;
          int numbers1=0;
          for(int w=0;w<cur.length();w++){
            if(cur.charAt(w)=='0') numbers0++;
            else numbers1++;
          }
            for(int j=m;j>=0;j--){
                for(int k=n;k>=0;k--){
                   if(k>=numbers1&&j>=numbers0){
                       if(i==0) dp[i][j][k]=1;//当处理到第一个位置的时候，并且当前剩余的j,k是当于该字符串中的0，1的时候，则直接存为1，这里进行初始化而已.
                        else dp[i][j][k]=Math.max(dp[i-1][j][k],1+dp[i-1][j-numbers0][k-numbers1]);//当end为0的时候，-1处则直接返回的是0.
                  }else{
                     dp[i][j][k]= i>0 ? dp[i-1][j][k]:0;//当end为0的时候，-1处则直接返回的是0.
                }
            }
            }
        }
         return dp[strs.length-1][m][n];
                }
                解法3：动态规划+空间优化（i位置只和i-1位置的元素有关。和背包问题类似的，可以重复利用一行数据或者一张表。）
                class Solution {
    public int[][][] memo;
    public int findMaxForm(String[] strs, int m, int n) {
        //从右到左的模型，返回数组i之前的最大的子集的数目。
        if(strs.length==0||strs==null) return 0;
        int[][] dp=new int[m+1][n+1];
         for(int i=0;i<strs.length;i++){//字符串是从0位置开始的。
          String cur=strs[i];
          int numbers0=0;
          int numbers1=0;
          for(int w=0;w<cur.length();w++){
            if(cur.charAt(w)=='0') numbers0++;
            else numbers1++;
          }
           for(int j=m;j>=numbers0;j--){
            for(int k=n;k>=numbers1;k--){
//前i个物品下，容量为j，k的情况下的最大容纳数目，或者价值1.
                dp[j][k]=Math.max(dp[j][k],1+dp[j-numbers0][k-numbers1]);
            }
           }
         }
          return dp[m][n];
                    }
                    14.给定一个无序的整数数组，找到其中最长上升子序列的长度。

                    示例:

                    输入: [10,9,2,5,3,7,101,18]
                    输出: 4
                    解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
                    解法一：暴力递归

                    解法2：动态规划
                    class Solution {
    int max=1;
    public int lengthOfLIS(int[] nums) {
       //最长的上升子序列的结束元素一定包含数组中的一个的。所以直接是以i为结尾的最长升序子序列。dp[i]是以i位置元素为结尾的最长升序的长度。
      if(nums==null||nums.length==0) return 0;
      int[] dp=new int[nums.length];
      for(int i=0;i<nums.length;i++){
          dp[i]=1;
          for(int j=0;j<i;j++){
              if(nums[i]>nums[j]) dp[i]=Math.max(dp[i],1+dp[j]);
          }
        max=Math.max(dp[i],max);
      }
      return max;
    }
                    }
                    解法3：二分查找和贪心
                    15.当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：

                    若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
                    或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
                    也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。

                    返回 A 的最大湍流子数组的长度。

 

                    示例 1：

                    输入：[9,4,2,10,7,8,8,1,9]
                    输出：5
                    解释：(A[1] > A[2] < A[3] > A[4] < A[5])（9，4，2，10，7，8）（8）（8，1）（1，9）
                    解法1：动态规划+滑动窗口
                    class Solution {
    public int maxTurbulenceSize(int[] A) {
     int n=A.length;
     if(n<=1)return n;
     int max=1;
     int start=0;//只要符号同号或者相等，最长湍流数组就要断。分两种情况 1.相等的时候  2.分不同号的时候。
     for(int i=0;i<n-1;i++){
       int c=Integer.compare(A[i],A[i+1]);    //前者更小则返回-1，更大则返回1，相等返回0
       if(c==0){
           max=Math.max(max,i-start+1);
           start=i+1;
           continue;
       }
       if((i<n-2)&&c*Integer.compare(A[i+1],A[i+2])!=-1){//是不是异号断开的。
          max=Math.max(max,i+1-start+1);
          start=i+1;
       }
     }
     max=Math.max(max,n-start);
     return max;
    }
                    }
                    16.有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。

                    现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。

 

                    示例 1：

                    输入:
                    n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
                    src = 0, dst = 2, k = 1
                    输出: 200
                    解释:
                    城市航班图如下


                    从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。

                    解法1：dij算法
                    class Solution {
   public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
       graph fly=new graph();
       for(int[] flight : flights){
         Integer fro=flight[0];
         Integer to=flight[1];
         Integer weig=flight[2];
         if(!fly.nodes.containsKey(fro)){//没有入图map的则先入map中再说。
            fly.nodes.put(fro,new node(fro));//nodes中没有对应节点则先创建加入。
         }
         if(!fly.nodes.containsKey(to)){
            fly.nodes.put(to,new node(to));
         }
         node f=fly.nodes.get(fro);
         node t=fly.nodes.get(to);
         edge cur=new edge(f,t,weig);
         f.nexts.add(t);
         f.toedges.add(cur);
         fly.edges.add(cur);
       }
        PriorityQueue<CityInfo> heap = new PriorityQueue((Comparator<CityInfo>) (city1, city2) -> city1.cost - city2.cost);//花费最小的小根堆。
        heap.add(new CityInfo(0,fly.nodes.get(src), K + 1)); // +1是关键，包括dst，共K+1跳。总共k个中间站，需要将数字
        while (!heap.isEmpty()) {
            CityInfo curInfo = heap.remove();
            if (curInfo.hop < 0) continue;//当该节点的所有边跳向的节点跳转次数已经
            if (curInfo.city.value == dst) return curInfo.cost;
            for(edge cur:curInfo.city.toedges){
                heap.add(new CityInfo(curInfo.cost + cur.weight, cur.to, curInfo.hop - 1));//跳往当前节点的所有的edge的一端，并记录了跳到其还剩余的次数。
            }
        }
        return -1;
    }
    private class CityInfo {//需要封装src在跳转的过程中到的各city节点，及cost，跳转到该节点的跳转次数，便于判断终止。
        int cost;
        int hop;
        node city;

        public CityInfo(int cost, node city, int hop) {
            this.cost = cost;
            this.city = city;
            this.hop = hop;
        }
    }
    public class graph{
        public HashMap<Integer,node> nodes;//当前节点的直接连接的所有的next nodes.
        public HashSet<edge> edges;
        public graph(){
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }
    public class node{
        public int value;
        public ArrayList<node> nexts;
        public ArrayList<edge> toedges;
        public node(int value1){
            value=value1;
            nexts=new ArrayList<>();
            toedges=new ArrayList<>();
        }
    }
    public class edge{
        public int weight;
        public node from;
        public node to;
        public edge(node f,node t,int w){
          weight=w;
          from=f;
          to=t;
        }
    }
                    }
（排列组合的，再看）17.给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列的字符串数量。

                    字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。

 

                    示例 1：

                    输入：n = 1
                    输出：5
                    解释：仅由元音组成的 5 个字典序字符串为 ["a","e","i","o","u"]
                    class Solution {//找出没增加一位字符的时候，5，4，3，2，1的增加关系。
                        dp[i][j]保存的状态为 长度为 i 且以大于等于 j 元音为结尾的字符串数量的和

                        所以有状态转移方程

                        dp[i][j] =dp[i-1][j] +dp[i][j-1];
    public int countVowelStrings(int n) {
        if(n==1) return 5;
        int a3=1;
        int b2=1;
        int c1=1;
        for(int i=2;i<n;i++){
         a3+=i;
         b2+=a3;
         c1+=b2;
        }
        return (5+(n-1)*4+a3*3+b2*2+c1);
    }
                    }


         //初始化n=1的情况
        for (int i=0;i<5;i++){
            dp[1][i]=1;
        }

        for (int i=2;i<=n;i++){
            //长度i的以u结尾的字符串可以由任意一个长度i-1的字符串结尾加个u得到
            dp[i][4]=dp[i-1][0]+dp[i-1][1]+dp[i-1][2]+dp[i-1][3]+dp[i-1][4];
            dp[i][3]=dp[i-1][0]+dp[i-1][1]+dp[i-1][2]+dp[i-1][3];
            dp[i][2]=dp[i-1][0]+dp[i-1][1]+dp[i-1][2];
            dp[i][1]=dp[i-1][0]+dp[i-1][1];
            //长度i的以a结尾的字符串只能由长度i-1的以a结尾的字符串结尾加个a得到
            dp[i][0]=dp[i-1][0];
        }

        //最终答案求个和就行啦
        return dp[n][0]+dp[n][1]+dp[n][2]+dp[n][3]+dp[n][4];

（再看）20.给你一个 m * n 的矩阵 mat 和一个整数 K，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和： 

                    i - K <= r <= i + K, j - K <= c <= j + K 
                    (r, c) 在矩阵内。（这表示answer的维度和mat是一样的，不然r和c是不可能全部在answer矩阵里面的。）
 

                    示例 1：

                    输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
                    输出：[[12,21,16],[27,45,33],[24,39,28]]
                    解法1：前缀和矩阵+动态规划
                    对于每一个answer[i][j]的元素值，都是mat中的一个小矩形区域的和（行：i-k---i+k。列：j-k---j+k），如下图所示，但是如果每一个元素都要计算一次的话，有很多次重复了，所以采用前缀和矩阵进行存储值dp[i][j]是表示原先矩阵中0 <= 行 <= i， 0 <= 列 <= j)范围内的和，并且可以动态规划求得到，这样当要求answer[i][j]的元素的时候，不用重复取计算mat的矩阵值了，直接通过dp矩阵中已经存好的值，进过简单运算后就可以得到。

                    1.首先求取前缀和矩阵值dp。
                    2.计算answer[i][j]元素对应的mat元素中的小矩形区域的范围值下标。
                    3.利用前缀和矩阵取值运算得到小矩形区域的和，如上图所示。
                    class Solution {
    public int[][] matrixBlockSum(int[][] mat, int K) {
      int row=mat.length;
      int line=mat[0].length;
      int[][] res=new int[row][line];
      int[][] dp=new int[row+1][line+1];
      for(int i=1;i<row+1;i++){
          for(int j=1;j<line+1;j++){
              dp[i][j]=mat[i-1][j-1]+dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1];//求i，j的值其实就是上面和左边再减掉对角那个矩形区域的重复加的值
          }
      }
      for(int i=0;i<row;i++){
          for(int j=0;j<line;j++){//遍历res中的位置。先计算它对应的矩形范围，再对照其dp中的值进行求解。（范围是左上角和右下角的位置）
            int x0=Math.max(i-K,0);
            int y0=Math.max(j-K,0);
            int x1=Math.min(i+K,row-1);
            int y1=Math.min(j+K,line-1);
            x1++;
            y1++;
            res[i][j]=dp[x1][y1]-dp[x1][y0]-dp[x0][y1]+dp[x0][y0];

          }
      }
      return res;
    }
                    }
（再看）21.环绕字符串中唯一的子字符串
                            难度中等109收藏分享切换为英文接收动态反馈
                    把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....". 
                    现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。 
                    注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
 
                    示例 1:
                    输入: "a"输出: 1解释: 字符串 S 中只有一个"a"子字符。
 
                    示例 2:
                    输入: "cac"输出: 2解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
 
                    示例 3:
                    输入: "zab"输出: 6解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.
                    解法1：一维动态规划+set去重超出内存
                    class Solution {
    public int findSubstringInWraproundString(String p) {
        if(p==null) return 0;
        int l=p.length();
        if(l==0) return 0;
        int[] save=new int[l];
        save[0]=1;
        int index=0;//记录上一次的不连续的尾巴索引
        Set<Character> repat=new HashSet<Character>();//p中重复的字符
        Set<String> repats=new HashSet<String>();//p中重复的字符串
        repat.add(p.charAt(0));
        for(int i=1;i<l;i++){//关键是如何去除重复的字串。
           int cur=save[i-1];
           if(repat.add(p.charAt(i))) cur++;
           if((p.charAt(i)-p.charAt(i-1)==1)||(p.charAt(i)-p.charAt(i-1)==-25)){//字串也不能重复，并且只有连续了，才可能被包含在s字符串中。
               for(int w=index;w<i;w++){//进行尝试各个index-i连续的子串的之间的小子串看是否重复或者已存在了。
                   if(!repats.add(p.substring(w,i+1))) break;//最长的都已经重复了的话，更短的就没有必要试了
                   else cur++;
               }
           }else{
               index=i;
           } 
           save[i]=cur;
        }
        return save[l-1];
    }
                    }

                    解法2：动态规划+机智去重（注意，子串是连续的并且，你看只有26个字母，对于同一个字母结尾的字串，只记录最长的长度就是字串的数目了。）
                    class Solution {
        if(p==null) return 0;
        int l=p.length();
        if(l==0) return 0;
       int[] save=new int[26];//save[i]表示在p字符串中以每一个字母元素为结尾的所有非空子串的个数。
        save[p.charAt(0)-'a']=1;
        int cn=1;
        for(int i=1;i<l;i++){
            int dis=p.charAt(i)-p.charAt(i-1);
            int index=p.charAt(i)-'a';
            if(dis==1||dis==-25)  cn++;//一直连续则一直加，直到断了重新开始计数
            else cn=1;//断裂了说明不再形成连续的子串了，并且同样要记录它，以字符它为积为的当前的子串个数，那么要从它这开始再重新寻找连续的。
            save[index]=Math.max(save[index],cn);//这里不仅可以去重单个重复字符的，还可以去重其它的，并且是每次走到任意一个位置的字符串都是要记录的，并非是连续到最后才记录最长的。（“za”是连续的，那么它就可以形成z,a和za三种字符，其中以a为结尾的就可以有2种的，除开头部开始的那个。因为是连续的，所以只有当有更长的连续才能覆盖它即避免了重复。）
        }
        int sum=0;
         for(int a:save) sum+=a;
         return sum; 
    }
                }
                (就是每个位数上的排列组合)22.给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。

                示例:

                输入: 2
                输出: 91
                解释: 答案应为除去 11,22,33,44,55,66,77,88,99外，在[0,100) 区间内的所有数字。
                解法1：动态规划+排列组合问题
                class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        //n=0,1
        //n=1,10种
        //n=2,9*9+10
        //n=3,9*9*8+9*9+10.
        //n=4,9*9*8*7+9*9*8+9*9+10.
        if(n==0) return 1;
        int ansys=10;
        int base=9;
        for(int i=1;i<Math.min(n,10);i++){
            base*=(10-i);
            ansys+=base;
        }
        return ansys;
    }
                }
                23.最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：

                Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
                Paste (粘贴) : 你可以粘贴你上一次复制的字符。
                给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。

                示例 1:

                输入: 3
                输出: 3
                解释:
                最初, 我们只有一个字符 'A'。
                第 1 步, 我们使用 Copy All 操作。
                第 2 步, 我们使用 Paste 操作来获得 'AA'。
                第 3 步, 我们使用 Paste 操作来获得 'AAA'。
                解法1：强行暴力进行时间复杂度O(n).

                        解法2：动态规划
（当时不理解的）24.附近的家居城促销，你买回了一直心仪的可调节书架，打算把自己的书都整理到新的书架上。

                你把要摆放的书 books 都整理好，叠成一摞：从上往下，第 i 本书的厚度为 books[i][0]，高度为 books[i][1]。

                按顺序 将这些书摆放到总宽度为 shelf_width 的书架上。

                先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelf_width），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。

                需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。

                每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。

                以这种方式布置书架，返回书架整体可能的最小高度。

 

                示例：



                输入：books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
                输出：6
                解释：
                3 层书架的高度和为 1 + 3 + 2 = 6 。
                第 2 本书不必放在第一层书架上。
                解法1：动态规划
                用 dp[i] 表示放置前 i 本书所需要的书架最小高度，初始值 dp[0] = 0，其他为最大值 1000*1000。遍历每一本书，把当前这本书作为书架最后一层的最后一本书，将这本书之前的书向后调整，看看是否可以减少之前的书架高度。状态转移方程为 dp[i] = min(dp[i] , dp[j - 1] + h)，其中 j 表示最后一层所能容下书籍的索引，h 表示最后一层最大高度。
                class Solution {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int[] dp=new int[books.length+1];
        Arrays.fill(dp,1000000);
        dp[0]=0;
        for(int i=1;i<books.length+1;i++){
            int width=0;
            int h=0;
            int j=i;
            while(j>0){
                width+=books[j-1][0];
                if(width>shelf_width) break;//当当前要放入的书的宽度加上要超过shelf_width的时候，直接就停止往该层放了，遍历下一本书。
                h=Math.max(h,books[j-1][1]);
                dp[i]=Math.min(dp[i],dp[j-1]+h);
                j--;
            }
        }
        return dp[books.length];
    }
                }
（k次交易+手续费）25.买卖股票的最佳时机含手续费
                        难度中等298收藏分享切换为英文接收动态反馈
                给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
                你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
                返回获得利润的最大值。
                注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
                示例 1:
                输入: prices = [1, 3, 2, 8, 4, 9], fee = 2输出: 8解释: 能够达到的最大利润:
                在此处买入 prices[0] = 1
                在此处卖出 prices[3] = 8
                在此处买入 prices[4] = 4
                在此处卖出 prices[5] = 9
                总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
                通解：
                T[i][k][0] = max(T[i - 1][k][0], T[i - 1][k][1] + prices[i])
                T[i][k][1] = max(T[i - 1][k][1], T[i - 1][k - 1][0] - prices[i])
                基准情况中，T[-1][k][0] = T[i][0][0] = 0 的含义和上文相同，T[-1][k][1] = T[i][0][1] = -Infinity 的含义是在没有进行股票交易时不允许持有股票。
                对于状态转移方程中的 T[i][k][0]，第 i 天进行的操作只能是休息或卖出，因为在第 i 天结束时持有的股票数量是 0。T[i - 1][k][0] 是休息操作可以得到的最大收益，T[i - 1][k][1] + prices[i] 是卖出操作可以得到的最大收益。注意到允许的最大交易次数是不变的，因为每次交易包含两次成对的操作，买入和卖出。只有买入操作会改变允许的最大交易次数。
                对于状态转移方程中的 T[i][k][1]，第 i 天进行的操作只能是休息或买入，因为在第 i 天结束时持有的股票数量是 1。T[i - 1][k][1] 是休息操作可以得到的最大收益，T[i - 1][k - 1][0] - prices[i] 是买入操作可以得到的最大收益。注意到允许的最大交易次数减少了一次，因为每次买入操作会使用一次交易。
                为了得到最后一天结束时的最大收益，可以遍历股票价格数组，根据状态转移方程计算 T[i][k][0] 和 T[i][k][1] 的值。最终答案是 T[n - 1][k][0]，因为结束时持有 0 份股票的收益一定大于持有 1 份股票的收益。
                解法1：递归
                1.买入之后我们可以保持不动，或者转入到卖出状态
                2.卖出之后可以继续保持不动(等待更好的股价出现)，或者立马再买一股
                买入和卖出的状态转换关系如下:（无论是买入或者卖出状态都可以有保持不动的状态，即保持不动状态是都行的；买入之后才能对应卖出；卖出之后才能再次买入）

                如果是当前是买入状态:
那么可以保持不动
或者马上卖掉(减去手续费)
                如果当前是卖出状态:
可以保持不动(等待更好的股价出现，暂时不买)
或者立马再买一股
                2.

//（即到最后的形成最大利润的情况可能是下面两者之间的最大值。 
                1.当前i位置保持不动（保持上上个操作形成的最大利润不动），卖出形成的利润（那么上上一个操作肯定是买入的操作形成的最大利润（保持不动利润是不变的））两个操作所形成的最大值
                2.当前i位置保持不动（保持上上个操作形成的最大利润不动），买入形成的最大利润两者最大的一种情况（那么上上一个操作形成的最大利润（保持不动利润是不变的）一定是卖出操作形成的）。两个操作所形成的最大值。

                class Solution {
    public int maxProfit(int[] prices, int fee) {
        if(prices.length==0) return 0;
        //前k个股票能够实现的最大利润
        return findmax(prices,0,0,fee);//这里其实传入了两种情况，买入或者保持不动，深度优先搜素的，最后其实就是只比较了传入0的时候，即买入或者保持不动情况下的二者那个利润最大。
    }
    public int findmax(int[] prices,int index,int status,int fee){//当i位置的时候就是，prices[i]买入、卖出或者保持不动情况下，返回（当前处理买入、卖出保持、不动+(i+1)位置处的最大利润）之和的最大利润。只能从左到右的情况下，因为只有再o位置的情况是确定的要么买入要么保持不动。（其实就是返回index位置之后的连续元素中所形成的最大利润（如何返回呢，就是返回当前的各种操作+(对应的index+1处操作下的最大利润)之和的最大值的一个））从最后开始逐渐往前的。
        if(index==prices.length) return 0;//当处理到超出数组范围的时候就结束了
        int retain=0;
        int save=0;
        int buy=0;
        retain=findmax(prices,index+1,status,fee);//当前index位置股票处理成保持不动的情况下的利润+(index+1的最大利润之和),那么当前是什么状态就是什么状态。
        if(status==1){//为1就卖出去股票的情况下的最大利润
            save=findmax(prices,index+1,0,fee)+prices[index]-fee;//index位置是处理成卖出股票的状态，那么后面的状态肯定是（1.保持不动 2.买入了股票的状态。）
        }else{
            buy=findmax(prices,index+1,1,fee)-prices[index];//index位置处理成买入的情况下，那么index+1之后的状态肯定是（1.保持不动 2.卖出了股票的状态。）之一，取更大的情况。
        }
            return Math.max(Math.max(retain,save),buy);
    }
                }

                解法2：记忆化搜索
     public int maxProfit(int[] prices, int fee) {
          if(prices.length==0) return 0;
          Map<pair,Integer> sa=new HashMap();
          return findmax(sa,prices,0,0,fee);//这里其实传入了两种情况，买入或者保持不动。
     }
     public int findmax(Map<pair,Integer> s,int[] prices,int index,int status,int fee){
         if(index==prices.length) return 0;//当处理到超出数组范围的时候就结束了
        int retain=0;
        int save=0;
        int buy=0;
        pair one=new pair(index,status);
        if(s.containsKey(one)) return s.get(one);//当前递归已经做过则直接取出返回。
        retain=findmax(s,prices,index+1,status,fee);//当前index位置股票处理成保持不动的情况下的利润+(index+1的最大利润之和),那么当前传入的是什么状态就直接传入什么状态。
        if(status==1){//为1就卖出去股票的情况下的最大利润
            save=findmax(s,prices,index+1,0,fee)+prices[index]-fee;//index位置是处理成卖出股票的状态，那么后面的状态肯定是（1.保持不动 2.买入了股票的状态。）
        }else{
            buy=findmax(s,prices,index+1,1,fee)-prices[index];//index位置处理成买入的情况下，那么index+1之后的状态肯定是（1.保持不动 2.卖出了股票的状态。）之一，取更大的情况。
        }
        s.put(one,Math.max(Math.max(retain,save),buy));//比较大小其实只有两者情况，1.保持不动的情况及2.当前操作为买入或者卖出的情况下，返回最大的那个。
        return Math.max(Math.max(retain,save),buy);
     }
                private class pair {
        private final int index;
        private final int status;
        pair(int index,int status) {
            this.index = index;
            this.status = status;
        }
        //这里需要实现自定义的equals和hashCode函数
        public int hashCode() {
            return index+status;
        }
        public boolean equals(Object obj) {//最好先传入object对象，更快。
            pair other = (pair)obj;
            if(this.index==other.index && this.status==other.status) {//调用对象的index、status属性和传入的对象进行比较
                return true;
            }
            return false;
        }
    }

                解法3：动态规划
 public int maxProfit(int[] prices, int fee) {//从左到右的，其实和上面的不对应的，相反的。因为最后一个位置是有买入、卖出、不动三种状态的，所以不好确定初始状态。
          int n=prices.length; 
          if(n==1) return 0;
          int[][] dp=new int[n][2];
          dp[0][0]=0;//index位置是卖出的情况。因为没有买入
          dp[0][1]=-prices[0];//index位置是买入的情况下是负收益的。
          for(int i=1;i<prices.length;i++){
              dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);//当天处理为卖出的话，比较i操作保持不动（利润那就是i-1处的，卖出的最大利润）和i卖出的情况下那个利润（i-1处买入的最大利润）+卖出形成的利润更大。
              dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);//买入的情况，比较i操作保持不动（利润那就是i-1处的，买入的最大利润）和i买入的情况下那个利润（i-1处卖出的最大利润）+买入形成的利润更大。
          }
          return Math.max(dp[n-1][0],dp[n-1][1]);
     }
                解法4：优化动态规划
     public int maxProfit(int[] prices, int fee){//（即到最后的形成最大利润的情况可能是   1.当前i保持不动和卖出形成的利润（那么上一个操作肯定是买入的）的最大值  2当前保持不动和买入形成的最大利润值。  两者最大的一种情况（那么上一个操作一定是卖出操作形成的最大利润）。
         int n=prices.length; 
         if(n==1) return 0;
         int save=0;
         int buy=-prices[0];
         for(int i=1;i<n;i++){//每一个数组都是有1.不动+2.（买入或者卖出的操作）两种情况。（那我不知到是二者那种路径形成最大值，所以需要两者都试验从而去比较得到最大值。）
             int s=save;
             int b=buy;
             save=Math.max(s,b+prices[i]-fee);//当天处理为卖出的话，比较i操作保持不动（利润那就是i-1处的，卖出情况下的最大利润）和i卖出的情况下那个利润（i-1处买入的最大利润）+卖出形成的利润更大。（正是因为有保持不动的情况，即可能卖出后保持不动，也可以是买入后保持不动）所以才需要两个变量来进行存储。（即到最后的形成最大利润的情况可能是1.当前i保持不动，卖出形成的利润 2当前保持不动，买入形成的最大利润两者最大的一种情况）
             buy=Math.max(b,s-prices[i]);//当前i处的操作是买的话，那么上一个操作形成的最大利润只能是（卖出）和保持不动的情况下的最大利润的比较
         }
         return Math.max(save,buy);
     }
（k次交易）26.给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

                设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

                注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

 

                示例 1:

                输入: [7,1,5,3,6,4]
                输出: 7
                解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
                解法1：动态规划

                class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length==1) return 0;
        int save=0;
        int buy=-prices[0];
        for(int i=1;i<prices.length;i++){//对于一层而言，形成最大利润的就是两种情况也就是两个之路，1.买入和不动的最大值  2.卖出不动的最大值（各支路形成一个最大的可以去除另一个支路）-----但是当两个支路的最大值并不能确定最终的最大值，只能继续往下面走直到最后。
          int s=save;
          int b=buy;
          save=Math.max(s,b+prices[i]);//当前操作是save的情况下最大利润（那么就是上一次买入所形成的最大利润+卖出的情况的利润）和保持不动的情况的最大利润（那就是本支路的上一个位置所形成的最大值。）那个更大就作为save的值
          buy=Math.max(b,s-prices[i]);//当前操作时buy的情况下和保持不动所产生的最大利润。
        }
        return Math.max(save,buy);
    }
    public int findmax(int[] s,int status,int index){//当前位置的处理状态。
        if(index==s.length) return 0;//当最后结束的时候，肯定就是
        int retain=0;
        int save=0;
        int buy=0;
        retain=findmax(s,status,index+1);//当前传入的是什么状态就是什么状态
        if(status==0){
            buy=findmax(s,1,index+1)-s[index];//当前操作是买入的话，那么后面一定是要卖出的。
        }else{
            save=findmax(s,0,index+1)+s[index];
        }
        return Math.max(Math.max(retain,save),buy);
    }
                }
（1次交易，这三种解法是基本一致的）27.给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

                如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。

                注意：你不能在买入股票前卖出股票。

 

                示例 1:

                输入: [7,1,5,3,6,4]
                输出: 5
                解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
                注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

                解法1：动态规划
                class Solution {
    public int maxProfit(int[] prices) {
     //只允许交易一次的话就是最大值-最小值，所以对于每个位置上的元素都有卖出股票所形成的最大利润
     if(prices==null||prices.length==0) return 0;
     int min=prices[0];
     int max=0;
     for(int i=1;i<prices.length;i++){
         max=Math.max(max,prices[i]-min);
         min=Math.min(min,prices[i]);
     }
     return max;
    }
                }
（2次交易）27.给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
                设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
                注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

                示例 1:

                输入: [3,3,5,0,0,3,1,4]
                输出: 6
                解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
                买入1次，算1次交易，那么卖出就不用再次进行计算了

                解法1：优化的动态规划
                class Solution {
    public int maxProfit1(int[] prices) {
        if(prices==null||prices.length==0) return 0;
        int n=prices.length;
        int buy1=-prices[0];
        int save1=0;
        int buy2=-prices[0];
        int save2=0;
        for(int i=1;i<n;i++){
            //1.处理第一次买入卖出
            buy1=Math.max(buy1,-prices[i]);
            save1=Math.max(save1,buy1+prices[i]);//在第一次真正的买入之后才能卖出
            //2.在第一次买入卖出的基础之上处理第二天的买入卖出的情况。
            buy2=Math.max(buy2,save1-prices[i]);//肯定是在第一次卖出之后才真正的买入第二次的，并且第二次买入所形成的理论是上一次最大的save1利润-当前买入的价格的
            save2=Math.max(save2,buy2+prices[i]);
        }
        return save2;
    }
（冷冻期）28.给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
                    设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

                    你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
                    卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
                    示例:

                    输入: [1,2,3,0,2]
                    输出: 3
                    解释: 对应的交易状态为: [买入, 卖出, 冷冻期,
                            解法1：相对1无限期交易的只是增加了一种状态而已
                    由于具有相同的 k 值，因此情况五和情况二非常相似，不同之处在于情况五有「冷却时间」的限制，因此需要对状态转移方程进行一些修改。

                    情况二的状态转移方程如下：
                    T[i][k][0] = max(T[i - 1][k][0], T[i - 1][k][1] + prices[i])
                    T[i][k][1] = max(T[i - 1][k][1], T[i - 1][k][0] - prices[i])
                    但是在有「冷却时间」的情况下，如果在第 i - 1 天卖出了股票，就不能在第 i 天买入股票。因此，如果要在第 i 天买入股票，第二个状态转移方程中就不能使用 T[i - 1][k][0]，而应该使用 T[i - 2][k][0]。状态转移方程中的别的项保持不变，新的状态转移方程如下：
                    T[i][k][0] = max(T[i - 1][k][0], T[i - 1][k][1] + prices[i])
                    T[i][k][1] = max(T[i - 1][k][1], T[i - 2][k][0] - prices[i])
                    class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = -prices[0];
        dp[2] = 0;
        int pre0 = dp[0];
        int pre2 = dp[2];
        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(dp[0], pre2);
            dp[1] = Math.max(dp[1], pre0 - prices[i]);
            dp[2] = dp[1] + prices[i];//就是当前天的卖出之后

            pre0 = dp[0];
            pre2 = dp[2];
        }
        return Math.max(dp[0], dp[2]);
    }  
                    }
                    (再看)29.给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。

 

                    示例 1：

                    输入：nums = [3,6,5,1,8]
                    输出：18
                    解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
                    解法1：动态规划（初始状态是，只有第一个出现对应余数的时候才会进行初始话。）
                    dp[i][0]表示nums[0...i]，即数组前i元素中相加和与3的模三余零的最大值。（对于原数组中的每一个元素都是要或者不要的，但是呢选择一个会影响最终形成的最大值，但是该选择不影响下一个和之前的。）
                    dp[i][1]表示 nums[0...i]模三余一的最大和
                    dp[i][2]表示 nums[0...i]模三余二的最大和
                    零状态：当前数字最大和模三余零
                    一状态：当前数字最大和模三余一
                    二状态：当前数字最大和模三余二
                    class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] save=new int[3];
        for(int i:nums){
             int t0=0,t1=0,t2=0;
            if(i%3==0){//只有当碰到第一个对应余数的数值时才进行初始化
                t0=save[0]+i;//余数为0直接加起来就可以，肯定是更大的，不涉及跨余数和进行相加的。
                t1=save[1]>0? Math.max(save[1] +i,save[1]):save[1];//当能贡献的时候，直接就分取和不取当前i那个更大。没有初始化的时候如果也是这样Math.max(save[1] +i,save[1])，那么就相当于直接取i值了
                t2=save[2]>0? Math.max(save[2] +i,save[2]):save[2];
            }else if(i%3==1){
                t0=save[2]>0?  Math.max(save[0],save[2]+i):save[0];//不能这样直接Math.max(save[0],save[2]+i):当只是4的时候，%为1，但是由于只有save[2]大于0的时候才会对其它的做出贡献，不然如果savo[2]也没有初始化了那么它就可能直接存储i值了，但是i现在时%1=0的了，不然就只能为原来的值save[0],因为其还没有进行初始化过程。
                t1= Math.max(save[0]+i,save[1]);//只有第一个出现余数为1的数，才会进行首次初始化的,首次进行初始化的 。
                t2=save[1]>0 ? Math.max(save[1]+i,save[2]):save[2];
            }else{
               t0=save[1]>0? Math.max(save[0],save[1]+i):save[0]; //这些只有经过初始化之后才能被执行的。（即比较当前余数为0的元素之和及余数为1和当前余数为2的元素之和那个更大取那个。）
                t1=save[2]>0? Math.max(save[2]+i,save[1]):save[1];
                t2=Math.max(save[0]+i,save[2]); //只有第一个出现余数为2的数的时候，才会进行首次初始化的，可能是直接save[0]的值直接加上当前这个的值（第一次初始化进行的或者已经初始过了，但是更小）或者直接为save[2](代表已经初始化过了才会这样)。
            }
                save[0]=t0;save[1]=t1;save[2]=t2;
        }
        return save[0];
    }
                    }
                    30.给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。

                    返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

 

                    示例：

                    输入：nums: [1, 1, 1, 1, 1], S: 3
                    输出：5
                    解释：

                    -1+1+1+1+1 = 3
                            +1-1+1+1+1 = 3
                            +1+1-1+1+1 = 3
                            +1+1+1-1+1 = 3
                            +1+1+1+1-1 = 3

                    一共有5种方法让最终目标和为3。
                    解法1：递归
                    class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if(nums==null||nums.length<1) return 0;
        return findTarget(nums,S,0);
    }
    public int findTarget(int[] nums, int s,int index) {//返回的是在index位置，处理成+或者-的时候，到末尾所能形成的和为s的可能数目。
        if(s==0&&index==(nums.length)) return 1;
        if(s!=0&&index==nums.length) return 0;
        int sub=s-nums[index];
        int add=s+nums[index];
        int res=0;
        res+=findTarget(nums,sub,index+1)+findTarget(nums,add,index+1);
        return res;   
    }
                    }

                    解法2：记忆化搜索
（再看）解法3：动态规划
                    class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
    for (int num : nums) {
            sum += num;
        }
        if (sum < S || ((sum + S) % 2 != 0)) { // 排除无法组合出S的情况
            return 0;
        }

        /*
            由于我们要 “使用nums数组，组合出S”
            因此，我们只需nums数组的部分元素，能够组合出 “(sum + S) / 2” 即可！
            基于上述思想，我们来构造一个dp数组：
                dp[i] 表示 nums数组中的部分元素，能够组成i的 组合数
            我们从第一个数开始遍历，填充dp数组：
                数字i 可由 任意一个nums数组中的值(num) 和 (i - num) 组成
         */
        int target = (sum + S) / 2;
        int[] dp = new int[target + 1]; // dp[i] 表示 nums数组，能够组成i
                            的组合数。
        dp[0] = 1;
        for (int num : nums) {
            for (int i = target; i>= num; i--) {
                dp[i] += dp[i - num];
            }
        }

        return dp[target];
    }
                    }
                    解法4：空间优化的动态规划
                    31.300. 最长递增子序列
                    难度中等1234收藏分享切换为英文接收动态反馈
                    给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
                    子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 
                    示例 1：
                    输入：nums = [10,9,2,5,3,7,101,18]输出：4解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
                    解法1：暴力动态规划
                    class Solution {
    public int lengthOfLIS(int[] nums) {
        //因为是最长的严格递增的子序列的，所以每一个index处都是有一个以它为结尾的最长子序列的，index可能是它前面的任意一个形成的，取最大的一个而已。
        int n=nums.length;
        if(n==1) return 1;
        int[] dp=new int[n];
        int maxres=1;
        for(int i=0;i<n;i++){
            int max=1;
            //因为每一个位置处为结尾的最长子序列都是和前面有关的，所以需要和前面的每一个元素进行比较，更大呢
            for(int j=i-1;j>=0;j--){//因为每一个元素都能够接在前面元素的最后的位置形成最长递增子序列。
                if(nums[i]>nums[j]) max=Math.max(dp[j]+1,max);//只有当i处的更大的时候才有必要和它连接起来，看下之前的max和dp[j]+1的那个更大，取更大的。          
            }
            dp[i]=max;
            maxres=Math.max(maxres,max);
        }
        return maxres;
    }
                    }
（再看）解法2：使用二分法来降低时间复杂度，第二次进行遍历可以进行避免或者说降低时间复杂度。
                    public int lengthOfLIS(int[] nums) {
      int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i <j) {//小于num的最右边的位置。
                int m = (i + j) / 2;
                if(tails[m]<num) i = m + 1;
                else j=m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }
                    (再看)32.131. 分割回文串（分割成的每一个子串都要是回文串。）
                    给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
                    返回 s 所有可能的分割方案。
                    示例:
                    输入: "aab"输出:
[
  ["aa","b"],
  ["a","a","b"]
]
                    解法1：回溯法其实就是递归而已。（只是有恢复现场操作而已）

（每一个位置都可以先截取一个（剩余的字符串也可以这样横向的进行）若满足回文条件，则先加入到list中，并接着下一个纵向遍历，然后接着截取两个（剩余的字符串也可以这样横向的进行），......）
                    class Solution {
    public List<List<String>> partition(String s) {
        if(s==null||s.length()==0) return new ArrayList<>();

        int len=s.length();
        List<List<String>> res=new ArrayList();
        Deque<String> pa=new ArrayDeque<String>();
        backstring(s,0,len,pa,res);
        return res;
    }

    public void backstring(String s,int start,int length,Deque<String> path,List<List<String>> save){
        if(start>=length){//当传入要判段的索引位置超出范围的时候直接加入，说明前面的截取的字符串都是回文的。
            save.add(new ArrayList(path));
            return;
        }
        for(int i=start;i<length;i++){
            if(!checkPalindrome(s,start,i)){//若执行到这里说明start-i要截取的字符串不是回文的，则直接结束该支路的递归
                continue;
            }
            path.add(s.substring(start,i+1));//是不会截取到i+1位置的的字符的。
            backstring(s,i+1,length,path,save);//截取到一个字串，就接着纵向递归去判断该字串的截取方法（1，2，3）形成的子串是否为回文
            path.removeLast();//因为递归结束该支路后呢，还需要从for循环的下一个位置开始递归下一支路。
        }
    }
        //传入将要截取的字串的左右索引，判断是否为回文串，fase则直接终止当前递归
    private boolean checkPalindrome(String str, int left, int right) {
        // 严格小于即可
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
                    }

                    (转换为背包问题)33.有一堆石头，每块石头的重量都是正整数。

                    每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

                    1.如果 x == y，那么两块石头都会被完全粉碎；
                    2.如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
                    最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。

 

                    示例：

                    输入：[2,7,4,1,8,1]
                    输出：1
                    解释：
                    组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
                    组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
                    组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
                    组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
                    解法1：动态规划+转换为背包问题,尽可能分成两堆一样的石头重量，所以就转换为背包容量限制为sum/2的情况下，即在物品体积为tone[]，价值为tone[]下所能装的最大的石头重量之和。
                    class Solution {
    public int lastStoneWeightII(int[] stones) {
    //求出限制的最大石头的重量。
    int sum=0;
    for(int i=0;i<stones.length;i++){
        sum+=stones[i];
    }
    int maxweights=sum/2;
    int[] dp=new int[maxweights+1];
    for(int i=1;i<=stones.length;i++){
        int weight=stones[i-1];

        int value=weight;
        for(int j=maxweights;j>=1;j--){
            if(j>=weight) dp[j]=Math.max(dp[j],dp[j-weight]+value);//之所以前面是需要加长1位，就是为了这里方便
        }
    }
    return sum-2*dp[maxweights];
    }
                    }
                    34.你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

                    给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

 

                    示例 1：

                    输入：[1,2,3,1]
                    输出：4
                    解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
                    解法1：动态规划
                    class Solution {
    public int rob(int[] nums) {
        int len=nums.length;
        if(len==0) return 0;
        if(len==1) return nums[0];
        int[] dp=new int[len];
        int max=0;
        dp[0]=nums[0];
        dp[1]=Math.max(nums[1],dp[0]);
        //dp[i]表示的是前i个房间的最大偷窃金额数目，不一定要包含第nums[i]元素的
        //因为对于每一个房间来说都有偷与不偷所形成的最大偷窃
                            之和的。所以比较那个更大则取那个呗。
        for(int i=2;i<len;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);//不偷i，就偷i-1和偷i的时候那就不能偷i-1。dp[i]表示的是到第i家的时候偷窃到的最大金额数目。
        }
        return dp[len-1];//最终一定是尾巴位置存储了最大偷窃你值。
    }
                    }

                    35你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

                    给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。

 

                    示例 1：

                    输入：nums = [2,3,2]
                    输出：3
                    解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
                    解法1：动态规划
                    上面的翻版，两个单排，即从o和从1开始的。
                    class Solution {
    public int rob(int[] nums) {
        int len=nums.length;
        if(len==1) return nums[0];
        //其实就是两个单排都可能形成最大值
        if(len==2) return Math.max(nums[0],nums[1]);
        int max=-1;
        int pref=nums[0];
        int prel=Math.max(pref,nums[1]);
        for(int i=2;i<=len-2;i++){
            pref=Math.max(prel,pref+nums[i]);
            int tmp=prel;
            prel=pref;
            pref=tmp;
        }
        max=Math.max(prel,max);
        pref=nums[1];
        prel=Math.max(pref,nums[2]);
        for(int i=3;i<=len-1;i++){
            pref=Math.max(prel,pref+nums[i]);
            int tmp=prel;
            prel=pref;
            pref=tmp;
        }
        max=Math.max(max,prel);
        return max;
    }
                    }

（再看）36.给你一个整数数组 arr 和一个整数 k。

                    首先，我们要对该数组进行修改，即把原数组 arr 重复 k 次。

                    举个例子，如果 arr = [1, 2] 且 k = 3，那么修改后的数组就是 [1, 2, 1, 2, 1, 2]。

                    然后，请你返回修改后的数组中的最大的子数组之和。

                    注意，子数组长度可以是 0，在这种情况下它的总和也是 0。

                    由于 结果可能会很大，所以需要 模（mod） 10^9 + 7 后再返回。 

 

                    示例 1：

                    输入：arr = [1,2], k = 3
                    输出：9
                    解法1:动态规划
                    class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        //其实就是翻版的连续子数组的最大值之和。
       //只是当arr大于0的时候是可以扩展的，具体扩展到那个位置不知道，所以要连着两个重复数组进行查找到最大子数组之和，然后当sum大于0的时候就重复进行扩展累加上sum就可以了。
       int len=arr.length;
       int endmaxi= arr[0]>0 ? arr[0]:0;//可以不取的，因为有负数的话。
       int max=endmaxi;
       int sum=arr[0];
       for(int i=1;i<Math.min(k,2)*len;i++){
           endmaxi=Math.max(endmaxi+arr[i%len],arr[i%len]);
           max=Math.max(max,endmaxi);
           if(i<len) sum+=arr[i];
       }
       while(sum>0&&(--k>=2)){

           max=(max+sum)%1000000007;//这里当两个数组连续形成的最大子树之和的时候，并且sum是大于0的，那么再加上一个子数组的时候，
       }
       return (int) max;
    }
                    }
                    37.5. 最长回文子串
                    难度中等3053收藏分享切换为英文接收动态反馈
                    给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
                    示例 1：
                    输入: "babad"输出: "bab"注意: "aba" 也是一个有效答案。
                    示例 2：
                    输入: "cbbd"输出: "bb"
                    解法1：暴力枚举
                    class Solution {
    public String longestPalindrome(String s) {
     //是子串
     //1.暴力解法，两个for循环，判断是否能是回文字串并且记录下该回文字串
    int max=0;
    int left=0;
    int right=0;
    for(int i=0;i<s.length();i++){
        for(int j=i;j<s.length();j++){
            if(teststr(s,i,j)){
                if((j-i+1)>max){
                    max=j-i+1;
                    left=i;
                    right=j;
                }
            }
        }
    }
    return s.substring(left,right+1);
     //2.动态规划
    }
    public boolean teststr(String str,int left,int right){
        while(left<right){
            if(str.charAt(left)!=str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
                    }
                    解法2：动态规划
                    public String longestPalindrome(String s) {
        //dp[i][j]:左闭右闭的表示是否为回文子串。
        int max=1;
        int left=0;
        int right=0;
        int len=s.length();
        boolean[][] dp=new boolean[len][len];
        char[] str=s.toCharArray();
        for(int i=0;i<len;i++){
            dp[i][i]=true;
        }
        for(int i=len-2;i>=0;i--){
            for(int j=i+1;j<len;j++){
                if(str[i]!=str[j]) {
                    dp[i][j]=false;
                }else{
                    if(j-i<3){//当相距小于3的时候直接判断首尾字符是否相等就可以，不用其它的，不然会越界
                      dp[i][j]=true;
                    }else{
                      dp[i][j]=dp[i+1][j-1];
                    }
                }
               if((dp[i][j])&&(j-i+1)>max){
                        max=j-i+1;
                        left=i;//是要切出最长回文的
                        right=j;
                } 
            }
        }
        return s.substring(left,right+1);
    }
                    解法3：动态规划之空间优化（就是在填二维表的基础之上，一行最长的数组可以复用每一行，因为只有前后两行是有关系的，并且从后面开始才不会被覆盖，后一行的当前列才能利用到前一行的该列之前的元素。）
 //空间优化
          int max=1;
        int left=0;
        int right=0;
        int len=s.length();
        boolean[] dp=new boolean[len];
        char[] str=s.toCharArray();
         for(int i=0;i<len;i++){
            dp[i]=true;
        }
        for(int i=len-2;i>=0;i--){
            for(int j=len-1;j>=i;j--){
                if(str[i]!=str[j]) {
                    dp[j]=false;
                }else{
                    if(j-i<3){//当相距小于3的时候直接判断首尾字符是否相等就可以，不用其它的，不然会越界
                      dp[j]=true;
                    }else{
                      dp[j]=dp[j-1];//因为当前行只和后面的一行有关系的，当前列和前一行的该列的-1是有关系的，所以需要从后面开始进行覆盖，可取用前一行的数据。
                    }
                }
               if((dp[j])&&(j-i+1)>max){
                        max=j-i+1;
                        left=i;
                        right=j;
                } 
            }
        }
          return s.substring(left,right+1);
（矩形相关）38.1504. 统计全 1 子矩形
                            难度中等74收藏分享切换为英文接收动态反馈
                    给你一个只包含 0 和 1 的 rows * columns 矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
 
                    示例 1：
                    输入：mat = [[1,0,1],
            [1,1,0],
            [1,1,0]]输出：13解释：有 6 个 1x1 的矩形。
                    有 2 个 1x2 的矩形。
                    有 3 个 2x1 的矩形。
                    有 1 个 2x2 的矩形。
                    有 1 个 3x1 的矩形。
                    矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。
                    解法1：预处理数组+动态规划
                    class Solution {
    public int numSubmat(int[][] mat) {
        int row=mat.length;
        int line=mat[0].length;
        int[][] former=new int[row][line];
     //定义dp[i][j] 为第i行，从0~j的全部为1的个数
        for(int i=0;i<row;i++){
            for(int j=0;j<line;j++){
                if(j==0) former[i][j]=mat[i][j]==1? 1:0;
                else former[i][j]=mat[i][j]==0 ? (0):(former[i][j-1]+1);
            }
        }
        int res=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<line;j++){
                int col=former[i][j];
                for(int k=i;k>=0&&col!=0;k--){
                    //统计每个位置上作为右下角元素的时候所能够形成的全1的矩阵，因为前面已经提前计算好了，作为一行的时候所能构成的最大的全1矩阵数目，所以进行高度方向统计即可
                    col=Math.min(col,former[k][j]);//横向和纵向取更下的即全形成全1的矩阵。
//需要确定该高度的宽度方向的最小的宽度才能形成完整的全1矩阵.
                    res+=col;//形成一次全1矩阵就加1.
                }
            }
        }
        return res;
    }
                    }
                    39.1477. 找两个和为目标值且不重叠的子数组
                    难度中等50收藏分享切换为英文接收动态反馈
                    给你一个整数数组 arr 和一个整数值 target 。
                    请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
                    请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
 
                    示例 1：
                    输入：arr = [3,2,2,4,3], target = 3输出：2解释：只有两个子数组和为 3 （[3] 和 [3]）。它们的长度和为 2 。
                    解法1：双指针求子数组和+动态规划不重叠的子数组
（若存在不重叠的子数组，那么肯定是前后有不同的子数组和为target，那么如何找出两个和最小的呢，其实就是找出所有目标和的子数组，然后两两进行遍历即可，但因找子数组目标和是一个一个找的，所以可以优化实现找两个不重叠子数组长度的和，即找到一个呢使用当前长度和之前left不重叠之前记录的最短的子数组的和，并且还记录之前所有的最小值，防止后面还有子数组满足目标和。）
                    class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        //因为要是不重叠的子数组，还必须用动态规划保存之前的
        int[] dp=new int[arr.length+1];//从1开始才是有意义的。即1对应的是arr的right=0位置前的。
        dp[0]=arr.length+1;//dp[i]表示前i个元素所形成的满足目标和的最短的子数组长度。
        int minres=arr.length+1;//记录的是当前j位置前的两个不重叠的子数组和的最小值
        for(int left=0,right=0,sum=0;right<arr.length;right++){
            sum+=arr[right];
            while(left<=right&&sum>target){//累加和呢大于目标和了，那么说明当前i-j是无法形成target的了，所以i++进行缩减，当sum小于的时候呢，j++进行扩大，因为都是从o开始的，所以能够遍历所有满足target的可能性。关键
            sum-=arr[left++];
                                    dp[i]：表示的是前i个位置中子数组和为target的最短的长度。
            }
            if(sum==target){
                int len=right-left+1;//当前满足的子数组长度。
                minres=Math.min(minres,len+dp[left]);//因为left-right是可以形成target目标和的，那就要再次进行长度len+dp[left]进行判断二者那个更小，当只形成一个子数组的时候，还是为原来取的较大的值。
                dp[right+1]=Math.min(len,dp[right]);//记录当前位置中一直存的都是前面元素最短的子数组长度。索引0之前的是存放在dp[1]之前中的。只要出现一个满足目标和的，首先就要比较和之前已经存在的目标和。
            }else{
                dp[right+1]=dp[right];//当不满足的时候直接存放前面一个的。因为即使没有形成和也是会记录前面的形成和为target了的。
            }
        }
        return minres>arr.length? -1:minres;
    }
                    }
                    40.935. 骑士拨号器
                    难度中等56收藏分享切换为英文接收动态反馈
                    国际象棋中的骑士可以按下图所示进行移动：
 .           

                    这一次，我们将 “骑士” 放在电话拨号盘的任意数字键（如上图所示）上，接下来，骑士将会跳 N-1 步。每一步必须是从一个数字键跳到另一个数字键。
                    每当它落在一个键上（包括骑士的初始位置），都会拨出键所对应的数字，总共按下 N 位数字。
                    你能用这种方式拨出多少个不同的号码？
                    因为答案可能很大，所以输出答案模 10^9 + 7。
                    解法1：动态规划
                    图片表示，当骑士处于“1”处时，下一跳将在“6”或“8”；骑士处于“4”处时，下一跳将在“3”或“0”或"9";骑士处于“0”处时，下一跳将在“4”或“6”…………

                    我们可以发现，1、3、7、9处于对称位置；2，8处于对称位置;4，6处于对称位置。因此，我们可以将数字分为4个状态，命名为A、B、C、D。其中A:{1,3,7,9}, B:{2,8}, C:{4,6}, D:{0}。

                    我们用f(X,n)表示：在状态X下，跳跃n步能够得到不同数字的个数。则状态转移方程为：

                    f(A,n)=f(B,n-1)+f(C,n-1)
                    f(B,n)=2*f(A,n-1)
                    f(C,n)=2*f(A,n-1)+f(D,n-1)
                    f(D,n)=2*f(C,n-1)

                    初始化是每种的状态数合记录为1种的，某种状态能够完全跳转到另一个状态就记录为其另一状态的个数，如对于An来说，Bn-1的状态和Cn-1的状态都能够任意的跳转到An里面的任意一个状态值；对于Bn状态来说呢，只有A状态能够跳转到，并且，An-1中的一般状态数目，就能够完全跳转到B状态。
                    class Solution {
    public int knightDialer(int n) {
        if(n==1) return 10;
        long a=1;
        long b=1;
        long c=1;
        long d=1;
        for(int i=1;i<n;i++){
            long e=(b+c)%1000000007;
            long f=2*a%1000000007;
            long g=(2*a%1000000007+d)%1000000007;
            long h=(2*c)%1000000007;
            a=e;
            b=f;
            c=g;
            d=h;
        }
        return (int)(((4*a%1000000007+2*b%1000000007)%1000000007+2*c%1000000007)%1000000007+d)%1000000007;
    }
                    }
（再看）41.给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：

                    每个节点都有 0 个或是 2 个子节点。
                    数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。（知识回顾：如果一个节点有 0 个子节点，那么该节点为叶节点。）
                    每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
                    在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。

 

                    示例：

                    输入：arr = [6,2,4]
                    输出：32
                    解释：
                    有两种可能的树，第一种的非叶节点的总和为 36，第二种非叶节点的总和为 32。

                    24            24
                            /  \          /  \
                    12   4        6    8
                            /  \               / \
                    6    2             2   4
                    解法1：区间dp
                    class Solution {
    public int mctFromLeafValues(int[] arr) {
        //暴力解法的话，直接就是求出所有的可能性，然后取一个最小值。
        int n=arr.length;
        if(n==2) return arr[0]*arr[1];
       int[][] max = new int[n][n];
        for (int j=0;j<n;j++) {
            int maxValue = arr[j];
            for (int i=j;i>=0;i--) {
                maxValue = Math.max(maxValue, arr[i]);
                max[i][j] = maxValue;
            }
        }
        int[][] dp=new int[n][n];
        for(int l=1;l<n;l++){//区间长度
            for(int start=0;start<n-l;start++){//起点
                 int min=Integer.MAX_VALUE;
                for(int mindle=start;mindle<start+l;mindle++){//在区间里面遍历中点。
                    min=Math.min(min,dp[start][mindle]+dp[mindle+1][start+l]+max[start][mindle]*max[mindle+1][start+l]);
                }
                dp[start][start+l]=min;//当区间长度是从0开始的时候，里面是不用遍历的了，相当于直接在这里赋值为最大值，即只有一个元素的时候其实是0的不算最大值。
            }
        }
        return dp[0][n-1];

    }
                    }
                    41.给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

                    示例 1:

                    输入: n = 12
                    输出: 3
                    解释: 12 = 4 + 4 + 4.
                    示例 2:

                    输入: n = 13
                    输出: 2
                    解释: 13 = 4 + 9.
                    解法1：暴力递归
 public int numSquares(int n) {
         return nums(n);
     }
     public int nums(int w){//返回当前目标值w，所挑选出的最小平方数形成该值的总个数
         if(w==0) return 0;
         int count=Integer.MAX_VALUE;
         for (int i = 1;i * i<=w; i++) {
             count=Math.min(count,nums(w-i*i)+1);//可以选当前平方和数和不选平方和数那个形成的个数更少。相当于就是对于一个总的n值，构成所有该值和的总的平方和数目中的最小值。树形下来的。
         }
         return count;
     }

                    解法2：广度遍历

 public int numSquares(int n) {
      Deque<Integer> paths=new LinkedList();
      Set<Integer> save=new HashSet();
      paths.offer(n);
      int level=0;
      while(!paths.isEmpty()){
          int i=0;
          int size=paths.size();
          level++; 
          while(i<size){
              i++;
              int cur=paths.poll();
              for(int j=1;j*j<=cur;j++){
                 int next=cur-(j*j);
                 if(next==0){
                     return level;//最先出现的就是最短的了。
                 }
                 if(save.add(next)){
                     paths.offer(next);
                 }
              }
          }
      }
      return -1;
     }


                    解法3：背包转化最小代价
                    class Solution {
    public int numSquares(int n) {
      if(n<=3) return n;
      int nums=(int)Math.sqrt(n)+1;
      int[] dp=new int[n+1];
      int[] weights=new int[nums];
      for(int i=1;i<=nums;i++){
          weights[i-1]=i*i;
      }
      Arrays.fill(dp,Integer.MAX_VALUE-1);（必须装满的）
      dp[0]=0;//你想得到嘛
      for(int i=1;i<nums;i++){
          for(int j=weights[i-1];j<=n;j++){//因为这里的j的值是需要依赖于前面一个j-1的容量值的，并且是求最小的代价值的，所以要从头开始遍历,相当于求出每一个值所对应的最小代价和，而不是像求最大值的时候从尾部开始遍历。
            dp[j]=Math.min(dp[j],dp[j-weights[i-1]]+1);
          }
      }
      return dp[n];
    }
                    }
（）42. 生成平衡数组的方案数
                    给你一个整数数组 nums 。你需要选择 恰好 一个下标（下标从 0 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。

                    比方说，如果 nums = [6,1,7,4,1] ，那么：

                    选择删除下标 1 ，剩下的数组为 nums = [6,7,4,1] 。
                    选择删除下标 2 ，剩下的数组为 nums = [6,1,4,1] 。
                    选择删除下标 4 ，剩下的数组为 nums = [6,1,7,4] 。
                    如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 平衡数组 。

                    请你返回删除操作后，剩下的数组 nums 是 平衡数组 的 方案数 。

 

                    示例 1：

                    输入：nums = [2,1,6,4]
                    输出：1
                    解释：
                    删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
                    删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
                    删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
                    删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
                    只有一种让剩余数组成为平衡数组的方案。
                    解法1：预处理前缀和
                    class Solution {
    public int waysToMakeFair(int[] nums) {
        int n=nums.length;
        if(n==1) return 1;
        int startou=0;
        int startji=0;
        int nowou=0;
        int nowji=0;
        int count=0;
        for(int i=0;i<n;i++){
            startou+=((i&1)==0)? nums[i]:0;//偶数累加
            startji+=((i&1)==0)? 0:nums[i];//奇数累加
        }
        for(int i=0;i<n;i++){
            int nowo=startji-nowji+nowou;
            int nowj=startou-nowou+nowji;
            if((i&1)==0){
                nowj-=nums[i];//要删除的是要删除原来的奇数偶数值中的。
                nowou+=nums[i];//累加偶数前缀和
            }else{
                nowo-=nums[i];//要删除的是要删除原来的奇数偶数值中的。
                nowji+=nums[i];
            }
            if(nowo==nowj) count++;
        }
        return count;
    }
                    }
                    42.416. 分割等和子集
                    难度中等644收藏分享切换为英文接收动态反馈
                    给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
                    注意:
                    1.每个数组中的元素不会超过 100
                    2.数组的大小不会超过 200
                    示例 1:
                    输入: [1, 5, 11, 5]

                    输出: true

                    解释: 数组可以分割成 [1, 5, 5] 和 [11].
                            解法1：背包问题转化
                    class Solution {
    public boolean canPartition(int[] nums) {
        //转化为被包问题
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        int target=0;
        if((sum&1)==0){
            target=sum/2;
        }else{
            return false;
        }
        int[] dp=new int[target+1];//前i个物品中，容量为target时所能存储的数组中元素的最大价值之和。
        for(int i=1;i<=nums.length;i++){
            int weight=nums[i-1];
            for(int j=target;j>=1;j--){
                if(j>=weight) dp[j]=Math.max(dp[j],dp[j-weight]+nums[i-1]);
            }
        }
        if(dp[target]==target) return true;
        else return false;
    }
                    }
                    43.给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

 

                    示例：

                    输入：
                    A: [1,2,3,2,1]
                    B: [3,2,1,4,7]
                    输出：3
                    解释：
                    长度最长的公共子数组是 [3, 2, 1] 。
                    解法1：动态规划
                    class Solution {
    public int findLength(int[] A, int[] B) {
      int lena=A.length;
      int lenb=B.length;
      int[][] dp=new int[lena+1][lenb+1];//(i-lena,与j-lenb两部分子数组之间的最长公共子数组的长度。并且是i,j各自开始连续形成的哦)
      int max=0;
      for(int i=lena-1;i>=0;i--){
          for(int j=lenb-1;j>=0;j--){
              if(A[i]==B[j]) dp[i][j]=1+dp[i+1][j+1];
              max=Math.max(max,dp[i][j]);
          }
      }
      return max;
    }
                    }
                    解法2：滑动窗口（我们注意到之所以两位置会比较多次，是因为重复子数组在两个数组中的位置可能不同。以 A = [3, 6, 1, 2, 4], B = [7, 1, 2, 9]为例，它们的最长重复子数组是 [1, 2]，在 A 与 B 中的开始位置不同。
                    但如果我们知道了开始位置，我们就可以根据它们将 A 和 B 进行「对齐」，即：
                    A = [3, 6, 1, 2, 4]
                    B =    [7, 1, 2, 9]
           ↑ ↑
                    此时，最长重复子数组在 A 和 B 中的开始位置相同，我们就可以对这两个数组进行一次遍历，得到子数组的长度）
                    public int findLength(int[] A, int[] B) {//枚举 A 和 B 所有的对齐方式,然后统计对齐位置开始重合的一段数组中的所有连续重复元素返回。
        int ma=A.length;
        int mb=B.length;
        int max=0;
        for(int i=0;i<ma;i++){//这里是B不动一直都是A移动对齐B的头部，然后比较对齐后重复数组区间内的最长重复子数组
            int l=Math.min(ma-i,mb);
            max=Math.max(max,maxLength(A,B,i,0,l));
        }
        for(int j=0;j<mb;j++){
            int l=Math.min(mb-j,ma);
            max=Math.max(max,maxLength(A,B,0,j,l));
        }//之所以要这样是因为最长的重复公共子数组可能是两数组中任意开头的两个数字，所以需要两两比较
        return max;
    }
     public int maxLength(int[] A, int[] B, int addA, int addB, int len) {//A中从addA开始，B中从addB开始，遍历它们后面之间所重合最短的长度，计算最长公共字串数组。len其实就是要比较的窗口的最短的窗口。
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;//公共子串就是相同就进行累加k呗。断了就需要进行再计数过。
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }
（矩形相关）44.一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

                    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

                    问总共有多少条不同的路径？

 

                    示例 1：


                    输入：m = 3, n = 7
                    输出：28
                    解法1：动态规划
                    class Solution {
    public int uniquePaths(int m, int n) {
        //dp[i][j]:到达i，j位置处的路径走法
        int[][] dp=new int[m][n];
        dp[0][0]=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i!=0&&j!=0){
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }else if(i==0&&j==0){
                    continue;
                }else if(i==0){
                    dp[i][j]=dp[i][j-1];
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
                    }
（再看）45.给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。

                    假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。

                    返回多边形进行三角剖分后可以得到的最低分。
 

                    示例 1：

                    输入：[1,2,3]
                    输出：6
                    解释：多边形已经三角化，唯一三角形的分数为 6。
                    示例 2：

                    解法1：区间DP
                    class Solution {
    public int minScoreTriangulation(int[] A) {
    int N=A.length;
    //从 0 ~ N-1 形成一个环
    //    1—3 
    //   /    \
    //  5      1
    //   \    /
    //     1—4
    // dp[left][right] 代表left~right区间形成的环的最小得分值
    int[][] dp=new int[N][N];
    for (int len=2;len<N;len++) { //枚举长度,从3开始
        for (int left=0;left<N-len;left++) { //枚举左端点
            //left+len-1<N
            int right=left+len;//这里就找到一个区间了，可以遍历中间点，去用
            //init
            dp[left][right]=Integer.MAX_VALUE;
            for (int i=left+1;i<right;i++) { //枚举区间内的所有的点(不包括端点)),将环分割成左右两部分
                dp[left][right]=Math.min(dp[left][right],dp[left][i]+dp[i][right]+A[i]*A[left]*A[right]);
            }
        }
    }
    return dp[0][N-1];
    }
                    }
（再看）46.96. 不同的二叉搜索树
                    难度中等961
                    给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
                    示例:
                    输入: 3输出: 5解释:给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

                    1         3     3      2      1
    \       /     /      / \      \
                    3     2     1      1   3      2
                            /     /       \                 \
                    2     1         2                 3
                    解法1：动态规划
                            .如果整数 1 - n 中的 k 作为根节点值，则 1 - k-1 会去构建左子树，k+1 - n 会去构建右子树。
                    左子树出来的形态有 a 种，右子树出来的形态有 b 种，则整个树的形态有 a∗b 种。以 kk 为根节点的 BSTBST 种类数 = 左子树 BSTBST 种类数 * 右子树 BSTBST 种类数

                    用 2、3 构建，和用 1、2 构建，出来的种类数是一样的，因为参与构建的个数一样。
                    再比如 2，3，4 和 1，2，3 都是连着的三个数，构建出的 BSTBST 的种类数相同，属于重复的子问题。
                    定义 dp[i] ：用连着的 i 个数（即i作为root根的时候），所构建出的 BSTBST 种类数
                    左子树用掉 j 个，则右子树用掉 i-j-1 个，能构建出 dp[j] * dp[i-j-1] 种不同的BST。
                    dp[i] = ∑dp[j]*dp[i-j-1], 0<=j<=i-1

                    状态转移方程
                    class Solution {
    public int numTrees(int n) {
         int[] G = new int[n + 1];
        G[0] = 1;//为o个数的时候能构成二叉搜索树的种数。
        G[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
                    }
（矩形相关）（类似38）47.1139. 最大的以 1 为边界的正方形
                            难度中等63收藏分享切换为英文接收动态反馈
                    给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 
                    示例 1：
                    输入：grid = [[1,1,1],[1,0,1],[1,1,1]]输出：9
                    示例 2：
                    输入：grid = [[1,1,0,0]]输出：1
                    解法1：暴力解法，统计以每一个元素为右下角所能构成的最大矩形

                    解法2：动态规划
                            left, right, down, up数组
                    采用4个方向数组来记录对应位置上可以向对应方向扩展的最大长度。
                    第一步：计算以当前节点（红色位置）往右的最大边长，往下的最大边长。取其中的小值minOne，因为要满足正方形。
                    第二步：通过上一步计算出来的边长，找到对应正方形中的另一个节点（黄色位置），找对应的左上数组，取其小值minTwo.
                            第三部：只要minOne <= minTwo，那么这个正方形就成立了，边长就是minOne。

                    class Solution {
    public int largest1BorderedSquare(int[][] grid) {
         int rows = grid.length;
        int cols = grid[0].length;
        int[][] left = new int[rows][cols];//从i,j位置往各个方向的最长的长度
        int[][] right = new int[rows][cols];
        int[][] up = new int[rows][cols];
        int[][] down = new int[rows][cols];
        rows--;
        cols--;
        int ret = 0;
        //下面开始初始化4个数组
        for (int i = 0; i <= rows; i++) {//left和up方向的，统计时从方向开始进行，并于简单的累加即可。
            for (int j = 0; j <= cols; j++) {
                if (grid[i][j] == 1) {//只有i,j位置的数为0了才可能计数
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;//初始化了0，0位置的元素。
                    up[i][j] = i > 0 ? up[i - 1][j] + 1 : 1;
                }
            }
        }
        for (int i = rows; i >= 0; i--) {
            for (int j = cols; j >= 0; j--) {
                if (grid[i][j] == 1) {
                    right[i][j] = j < cols ? right[i][j + 1] + 1 : 1;
                    down[i][j] = i < rows ? down[i + 1][j] + 1 : 1;
                }
            }
        }
        //下面计算结果
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                int minOne = Integer.min(right[i][j], down[i][j]);//遍历每一个位置的元素，取出右和下方向的最短的长度。
                for (; minOne > 0; minOne--) {//可能当最短边处还是不能构成全1的时候，就要往回走进行寻找了。
                    int iPoint = i + minOne - 1;//根据上面的最短长度，确定对应可能形成矩形的右下角位置。直接是索引+长度-1.
                    int jPoint = j + minOne - 1;
                    int minTwo = Integer.min(left[iPoint][jPoint], up[iPoint][jPoint]);//取出右下角的上和左的最短长度。
                    if (minOne <= minTwo) {//当两个位置有重合时，构成矩形，取最短边进行比较
                        ret = Math.max(ret, minOne);//minOne是从大到小，找到的第一个就是最大的
                        break;
                    }
                }
            }
        }
        return ret * ret;
    }
                    }

                    48.968. 监控二叉树
                    难度困难258收藏分享切换为英文接收动态反馈
                    给定一个二叉树，我们在树的节点上安装摄像头。
                    节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
                    计算监控树的所有节点所需的最小摄像头数量。
 
                    示例 1：

                    输入：[0,0,null,0,0]输出：1解释：如图所示，一台摄像头足以监控所有节点。
 解法1：树型dp
/**
  * Definition for a binary tree node.
  * public class TreeNode {
  *     int val;
  *     TreeNode left;
  *     TreeNode right;
  *     TreeNode(int x) { val = x; }
  * }
  */
                    class Solution {
    public int minCameraCover(TreeNode root) {
        /*树形dp的通用解法
     状态定义：（装或者不装，但是对于不装呢，可能又有两状态，即它自己能不能被覆盖到呢。）不要考虑上面一层的事情
    状态0：当前节点安装相机的时候，需要的最少相机数
    状态1： 当前节点不安装相机，但是能被覆盖到的时候，需要的最少相机数
    状态2：当前节点不安装相机，也不能被覆盖到的时候，需要的最少相机数
    最后我们的解当然就是根节点的状态0和状态1中的最小值啦

    而每一个节点的状态只和它的左孩子和右孩子有关，因此状态的转移方式如下：

    1.安装相机，其左孩子节点和右孩子节点都可以安装或者不装，但是总相机数+1
    dp[0] = Math.min(left[0], Math.min(left[1], left[2])) + Math.min(right[0], Math.min(right[1], right[2])) + 1
    2.不安装相机，但是能被覆盖到，说明其孩子节点至少有一个安装了相机，因为自己不安装相机，如果孩子节点也不安装，那子节点只能是已被覆盖到的
    dp[1] = Math.min(left[0] + Math.min(right[0], right[1]), right[0] + Math.min(left[0], left[1]))
    3.不安装相机，也不能被覆盖到，说明其孩子节点都没有安装相机，因为自己没有安装相机，其孩子节点也必须是已被覆盖到的，不管它自己，他自己是一定要被覆盖到的。
        dp[2] = left[1] + right[1]*/
        int[] res=heplfind(root);
        return Math.min(res[0],res[1]);
    }
    public int[] heplfind(TreeNode head){
       int[] dp = new int[3]; 
        if (head == null) {
            dp[0] = Integer.MAX_VALUE / 2; 
            dp[2] = Integer.MAX_VALUE / 2; 
            return dp;
        }
        int[] left=heplfind(head.left);
        int[] right=heplfind(head.right);
        1.dp[0]=Math.min(left[0],Math.min(left[1],left[2]))+Math.min(right[0],Math.min(right[1],right[2]))+1;//当前节点此树安装监控所需要的最少监控数目。
        2.dp[1]=Math.min(left[0]+Math.min(right[0],right[1]),right[0]+Math.min(left[0],left[1]));//不安装还能被覆盖，说明左右字节点至少一个安装了的，并且都被覆盖了的。
        3.dp[2]=left[1]+right[1];// //当前节点此树不安装监控并且没有被覆盖时所需要的最少监控数目，为了保证它的子树都被覆盖了，那么左右树肯定是被覆盖了但是没有监控。
          return dp;
    }
                    }
（再看）49. 在LeetCode商店中，有许多在售的物品。

                    然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。

                    现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。

                    每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。

                    任意大礼包可无限次购买。

                    示例 1:

                    输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
                    输出: 14
                    解释:
                    有A和B两种物品，价格分别为¥2和¥5。
                    大礼包1，你可以以¥5的价格购买3A和0B。
                    大礼包2， 你可以以¥10的价格购买1A和2B。
                    你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
                    解法1：dfs
                    class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        //不能超过needs的所购物品的总数的。
        //对于每一个物品呢，可以要与不要构成，当不要只能是礼包中的物品数量超范围了才行，
        //对于每一次的needs返回的最小花费数目。
        //对于每一个  needs状态  都有单独购买全部和礼拜购买两种状态的。
        return help(price,special,needs);
    }
    public int help(List<Integer> price, List<List<Integer>> special, List<Integer> needs){
        int res=find(price,needs);//当前need状态下单独购买所有物品的花费
        int j=0;
        for(List<Integer> cur:special){//不要当前礼包的话就继续循环
            List<Integer> clone=new ArrayList(needs);//对于每一个needs状态下都循环遍历下礼包，看是否能够被购买所有礼包中的每一个礼包从而形成另一个needs状态。
            //当对另一个礼包进行时，又会重新进行赋值的。因为即使该礼包能够被购买，也可以有不购买的状态。
            for(j=0;j<clone.size();j++){
                int dif=clone.get(j)-cur.get(j);
                if(dif<0) break;//当差值是小于0的，即礼包中某种物品数量是大于所需要的，那么就不能购买当前礼包了
                clone.set(j,dif);//当能够购买该种类的时候，则更新该种类的物品数量
            }
            if(j==clone.size()) res=Math.min(res,cur.get(j)+help(price,special,clone));//对当前满足的礼拜，取和不取所形成的最小值。要当前礼包时进行递归。
        }
        return res;//当礼拜中所有的物品都无法满足的时候，就是得全部单独购买，往上回溯的过程了，即前面。
    }
    public int find(List<Integer> price,List<Integer> need){//返回当前需求状态单独购买的时候的花费，便于和礼包购买的时候进行比较
        int size=price.size();
        int sum=0;
        for(int i=0;i<size;i++){
            sum+=price.get(i)*need.get(i);
        }
        return sum;
    }
                    }
                    50.464. 我能赢吗
                    难度中等198收藏分享切换为英文接收动态反馈
                    在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到或超过 100 的玩家，即为胜者。
                    如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
                    例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
                    给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
                    你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
                    示例：
                    输入：
                    maxChoosableInteger = 10
                    desiredTotal = 11
                    输出：
                    false
                    解释：无论第一个玩家选择哪个整数，他都会失败。
                    第一个玩家可以选择从 1 到 10 的整数。
                    如果第一个玩家选择 1，那么第二个玩家只能选择从 2 到 10 的整数。
                    第二个玩家可以通过选择整数 10（那么累积和为 11 >= desiredTotal），从而取得胜利.
                            同样地，第一个玩家选择任意其他整数，第二个玩家都会赢。
                    解法1：记忆化搜索

（再看）51.1048. 最长字符串链
                    难度中等97收藏分享切换为英文接收动态反馈
                    给出一个单词列表，其中每个单词都由小写英文字母组成。
                    如果我们可以在 word1 的任何地方添加一个字母使其变成 word2，那么我们认为 word1 是 word2 的前身。例如，"abc" 是 "abac" 的前身。
                    词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word_1 是 word_2 的前身，word_2 是 word_3 的前身，依此类推。
                    从给定单词列表 words 中选择单词组成词链，返回词链的最长可能长度。
 
                    示例：
                    输入：["a","b","ba","bca","bda","bdca"]输出：4解释：最长单词链之一为 "a","ba","bda","bdca"。
 
                    解法1：尾部动态规划+双指针判断是否是前身
                    class Solution {
    public int longestStrChain(String[] words) {
        int n=words.length;
        if(n<=1) return 1;
        Arrays.sort(words,(String a,String b) ->(a.length()-b.length()));//当时没有考虑到。
        int[] dp=new int[n];
        dp[0]=1;
        int res=0;
        for(int i=1;i<n;i++){
            dp[i]=1;
            for(int j=i-1;j>=0;j--){
            int curmax=1;
            if(help(words[i],words[j])){
                curmax=curmax+dp[j];//记录i,j形成磁链的时候的长度。
                dp[i]=Math.max(curmax,dp[i]);//前面一次形成的磁链和当前的形成的长度取更长的。
            }
            }
            res=Math.max(res,dp[i]);
        }
        return res;
    }
    public boolean help(String a,String b){
        int lena=a.length();
        int lenb=b.length();
        if((lena-lenb)!=1) return false;
        else {
            for(int i=0;i<lena;i++){
                String sa=null;
                if(i==0){
                    sa=a.substring(1,lena);
                }else if(i==(lena-1)){
                    sa=a.substring(0,lena-1);
                }else{
                    sa=a.substring(0,i)+a.substring(i+1,lena);
                }
                if(b.equals(sa)) return true;
            }
            return false;
        }
        
    }
                    }
 private boolean isPredecessor(String a, String b) {
    int i = 0, j = 0;
    int m = a.length(), n = b.length();
    if ((m + 1) != n) return false;
    while (i < m && j < n) {
        if (a.charAt(i) == b.charAt(j)) i++;
        j++;
    }
    return i == m;
                    }
                    51.647. 回文子串
                    难度中等479收藏分享切换为英文接收动态反馈
                    给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
                    具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 
                    示例 1：
                    输入："abc"输出：3解释：三个回文子串: "a", "b", "c"

                    解法1：动态规划
                    class Solution {
    public int countSubstrings(String s) {
        int len=s.length();
        char[] save=s.toCharArray();
        boolean[][] dp=new boolean[len][len];
        for(int i=0;i<len;i++){
            dp[i][i]=true;
        }
        int res=0;
        for(int i=len-1;i>=0;i--){
            for(int j=i;j<=len-1;j++){
                if(i==j){
                    res++;
                    continue;
                }
                if(save[i]!=save[j]){
                    dp[i][j]=false;
                }else{
                   if(j-i<3){//前后两个是相等的，那么就
                        dp[i][j]=true;
                   }else{
              dp[i][j]=dp[i+1][j-1];
                    }
                }
                if(dp[i][j]) res++;
            }
        }
        return res;
    }
                    }
                    解法2：空间优化
                    解法3：中心拓展
                    class Solution {
                        public:
                        int countSubstrings(string s) {
                            int num = 0;
                            int n = s.size();
                            for(int i=0;i<n;i++)//遍历回文中心点，原串中的每一个字符都可能是单回文中心和双回文中心。
                            {
                                for(int j=0;j<=1;j++)//j=0,中心是一个点，j=1,中心是两个点
                                {
                                    int l = i;
                                    int r = i+j;
                                    while(l>=0 && r<n && s[l--]==s[r++]) num++;
                                }}
                            return num;
                        }
                    };
                    52.64. 最小路径和
                    难度中等782收藏分享切换为英文接收动态反馈
                    给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
                    说明：每次只能向下或者向右移动一步。
                    示例 1：

                    输入：grid = [[1,3,1],[1,5,1],[4,2,1]]输出：7解释：因为路径 1→3→1→1→1 的总和最小。
                    解法1：动态规划
                    class Solution {
    int[][] dp;
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        dp=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int cur=grid[i][j];
                if(i==0&&j==0){
                    dp[i][j]=cur;
                }else if(i==0&&j!=0){
                    dp[i][j]=cur+dp[i][j-1];
                }else if(i!=0&&j==0){
                    dp[i][j]=cur+dp[i-1][j];
                }else{
                     dp[i][j]=Math.min(cur+dp[i-1][j],cur+dp[i][j-1]);
                } 
            }
        }
        return dp[m-1][n-1];
    }
                    }
（经典，再看）53.72. 编辑距离
                    难度困难1410收藏分享切换为英文接收动态反馈
                    给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
                    你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符
 
                    示例 1：
                    输入：word1 = "horse", word2 = "ros"输出：3解释：
                    horse -> rorse (将 'h' 替换为 'r')
                    rorse -> rose (删除 'r')
                    rose -> ros (删除 'e')
                    示例 2：
                    解法1：动态规划
                    第 1 步：定义状态
                    dp[i][j] 表示：将 word1[0..i) 转换成为 word2[0..j) 的方案数。

                    说明：由于要考虑空字符串，这里的下标 i 不包括 word[i]，同理下标 j 不包括 word[j]。

                    第 2 步：推导状态转移方程
                    注意：由于要考虑空字符串，针对 word1 和 word2 的讨论需要将下标减 11，这一点可以通过如下描述或者参考代码进行理解。

                    情况 1：如果 word1[i - 1] == word2[j - 1]，即当前考虑的两个字符串的最后一个字符相等，此时它们的编辑距离就等于它们去掉了最后一个字符以后的编辑距离，dp[i][j] = dp[i - 1][j - 1]；



                    情况 2：如果 word1[i - 1] != word2[j - 1]，此时编辑距离是以下三种情况的最小者（根据题目的定义，编辑距离的定义取最小者）。

                    情况 2.1：在当前 word1 后面加上与当前 word2 最后一个字符相等的字符（操作次数 + 1），此时编辑距离 dp[i][j] = dp[i][j - 1] + 1；



                    情况 2.2 ：去掉当前 word1 后面最后一个字符（操作次数 + 1），此时编辑距离 dp[i][j] = dp[i - 1][j] + 1；



                    情况 2.3：将当前 word1 后面最后一个字符替换成当前 word2最后一个字符（操作次数 + 1），此时编辑距离 dp[i][j] = dp[i - 1][j - 1] + 1。



                    综上所述：dp[i][j] 等于以上 4者的最小值。即：


                    dp[i][j] = min(dp[i - 1][j - 1], dp[i][j - 1] + 1, dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1)
                    class Solution {
    public int minDistance(String word1, String word2) {
        int len1=word1.length();
        int len2=word2.length();
        int[][] dp=new int[len1+1][len2+1];
        //dp[i][j]:的定义是【0，i）--[0,j)的转换方案数目。
        for(int i=0;i<=len1;i++){
            dp[i][0]=i;
        }
        for(int j=0;j<=len2;j++){
            dp[0][j]=j;
        }
        char[] char1=word1.toCharArray();
        char[] char2=word2.toCharArray();
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(char1[i-1]==char2[j-1]){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                   /* // 否则在以下三种情况中选出步骤最少的，这是「动态规划」的「最优子结构」
                // 1、在下标 i 处插入一个字符
                int insert = dp[i][j - 1] + 1;
                // 2、替换一个字符
                int replace = dp[i - 1][j - 1] + 1;
                // 3、删除一个字符
                int delete = dp[i - 1][j] + 1;
*/
                    dp[i][j]=Math.min(Math.min(dp[i-1][j]+1,dp[i][j-1]+1),dp[i-1][j-1]+1);
                    //将w1的末尾字符替换为w2的末尾字符时。
                    //删除w1的末尾字符时。
                    //在w1末尾添加上一个w2末尾的元素的时候。
                }
            }
        }
        return dp[len1][len2];
                            54.1143. 最长公共子序列
                            难度中等366收藏分享切换为英文接收动态反馈
                            给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
                            一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
                            例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
                            若这两个字符串没有公共子序列，则返回 0。
 
                            示例 1:
                            输入：text1 = "abcde", text2 = "ace" 输出：3  解释：最长公共子序列是 "ace"，它的长度为 3。
                            解法1：动态规划
                            class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1=text1.length();
        int len2=text2.length();
        int[][] dp=new int[len1+1][len2+1];
        //dp[i][j]:是两个字符串[0,i）与[0，j）之间的最长公共子序列长度。
        char[] char1=text1.toCharArray();
        char[] char2=text2.toCharArray();
        int max=0;
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(char1[i-1]==char2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    //当在i-1和j-1处的字符不相同的时候，因为【i-1,j】已经演算过了，同时【i,j-1】也已经演算过了。
                    //当不相等的时候，所以i,j处的字符不会成对的出现在公共子序列中，但是可能单独的出现，所以看两者单独出现的时候那个更大就取那个。
                    //即那两个字符就要分别去掉一个的情况下看那种情况下的公共子序列更大。
                    //若 当前两个字符 不等：当前状态 = 两个元素任一元素的前一个状态 的 最大值
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
                            }
                            55.746. 使用最小花费爬楼梯
                            难度简单513收藏分享切换为英文接收动态反馈
                            数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
                            每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
                            请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。
 
                            示例 1：
                            输入：cost = [10, 15, 20]输出：15解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
 示例 2：
                            输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]  输出： 6解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
                            解法1：动态规划
                            class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
        //登顶就是跳到了n+1的位置了，即索引是n的位置，即台阶的长度处，每跳一格就需要支付当前所在台架的体力花费值。
        //因为一开始是在最底板的时候，所以往上可以跳1格或者两格并且是不花体力的。
       int[] dp=new int[n+1];
        dp[0]=dp[1]=0;//因为从最底部开始跳的时候，那个台架是没有体力花费的，所以是最小的。
        //dp[i]:表示跳到下标为i的所花费的最小体力值，当小标为n的时候就是登顶了。
        for(int i=2;i<=n;i++){
            dp[i]=Math.min(cost[i-1]+dp[i-1],cost[i-2]+dp[i-2]);
        }
        return dp[n];
    }
                            }
                            56.环形数组中的连续子数组的最大和
                            解法1：分两种情况，1.去掉环 2.跨环形成最大连续子数组和
import java.util.*;
                            public class Main{
                                public static void main(String[] args){
                                    Scanner sc=new Scanner(System.in);
                                    int t=sc.nextInt();
                                    for(int i=0;i<t;i++){
                                        int n=sc.nextInt();
                                        int[] save=new int[n];
                                        int sum=0;
                                        for(int j=0;j<n;j++){
                                            save[j]=sc.nextInt();
                                            sum+=save[j];
                                        }
                                        //两种情况形成最大和的
                                        1.不跨越环
                                        2.跨越环 比较两个那个更大即可。
                                        最后两种情况去比较看那个更大即可
                                        int min=Integer.MAX_VALUE;
                                        int mincur=0;
                                        int res=0;
                                        int maxcur=0;
                                        for(int j=0;j<n;j++){
                                            maxcur=Math.max(maxcur+save[j],save[j]);
                                            mincur=Math.min(mincur+save[j],save[j]);
                                            min=Math.min(min,mincur);
                                            res=Math.max(res,maxcur);
                                        }
                                        System.out.println(Math.max(sum-min,res));
                                    }


                                }
                            }
}
