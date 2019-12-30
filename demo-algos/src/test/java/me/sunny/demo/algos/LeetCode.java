package me.sunny.demo.algos;

import me.sunny.demo.algos.leetcode.HappyNumber;
import me.sunny.demo.algos.leetcode.LargestRectInHistogram;
import me.sunny.demo.algos.leetcode.RemoveKdigits;
import me.sunny.demo.algos.leetcode.RemoveLinkedListElements;
import me.sunny.demo.algos.leetcode.RemoveLinkedListElements.ListNode;
import org.junit.Test;
import org.testng.Assert;

public class LeetCode {

    @Test
    public void testRemoveKdigits11() {
        String num = "10200";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution1(num, 1), "200");
    }

    @Test
    public void testRemoveKdigits12() {
        String num = "10";
        int k = 2;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution1(num, 1), "0");
    }

    @Test
    public void testRemoveKdigits13() {
        String num = "10";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution1(num, 1), "0");
    }

    @Test
    public void testRemoveKdigits21() {
        String num = "10200";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution2(num, 1), "200");
    }

    @Test
    public void testRemoveKdigits22() {
        String num = "10";
        int k = 2;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution2(num, 1), "0");
    }

    @Test
    public void testRemoveKdigits23() {
        String num = "10";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution2(num, 1), "0");
    }

    @Test
    public void testRemoveKdigits24() {
        String num = "112";
        int k = 1;
        RemoveKdigits removeKdigits = new RemoveKdigits();
        Assert.assertEquals(removeKdigits.solution2(num, 1), "11");
    }

    @Test
    public void testLargestRectInHistogram1() {
        int[] heights = {1};
        LargestRectInHistogram largestRectInHistogram = new LargestRectInHistogram();
        Assert.assertEquals(largestRectInHistogram.largestRectangleArea(heights), 1);
    }

    @Test
    public void testLargestRectInHistogram2() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        LargestRectInHistogram largestRectInHistogram = new LargestRectInHistogram();
        Assert.assertEquals(largestRectInHistogram.largestRectangleArea(heights), 10);
    }

    @Test
    public void testLargestRectInHistogram3() {
        int[] heights = {2, 1, 5, 6, 2, 3, 0};
        LargestRectInHistogram largestRectInHistogram = new LargestRectInHistogram();
        Assert.assertEquals(largestRectInHistogram.largestRectangleArea2(heights), 10);
    }

    @Test
    public void testHappyNumber() {
        int n = 19;
        HappyNumber happyNumber = new HappyNumber();
        Assert.assertTrue(happyNumber.isHappy(n));
    }

    @Test
    public void testRemoveLinkedListElements() {
        RemoveLinkedListElements removeLinkedListElements = new RemoveLinkedListElements();
        ListNode list = removeLinkedListElements.new ListNode(-1);
        ListNode node = removeLinkedListElements.new ListNode(1);
        node.setNext(null);
        list.setNext(node);
        removeLinkedListElements.removeElements(list, 1);
    }

}
