package me.sunny.demo.dp.behavioral.visitor.accoutbook;

import java.util.ArrayList;
import java.util.List;

/**
 * 账本类（相当于ObjectStructure）
 *
 * 账本类当中有一个列表，这个列表是元素（Bill）的集合，这便是对象结构的通常表示，
 * 它一般会是一堆元素的集合，不过这个集合不一定是列表，也可能是树，链表等等任何数据结构，甚至是若干个数据结构。
 *
 * 其中show方法，就是账本类的精髓，它会枚举每一个元素，让访问者访问。
 */
public class AccountBook {

    //单子列表
    private List<Bill> billList = new ArrayList<Bill>();

    //添加单子
    public void addBill(Bill bill){
        billList.add(bill);
    }

    //供账本的查看者查看账本
    public void show(Viewer viewer) {
        for (Bill bill : billList) {
            bill.accept(viewer);
        }
    }
}
