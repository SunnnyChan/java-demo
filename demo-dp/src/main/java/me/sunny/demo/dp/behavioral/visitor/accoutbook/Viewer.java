package me.sunny.demo.dp.behavioral.visitor.accoutbook;

import me.sunny.demo.dp.behavioral.visitor.role.Visitor;
/**
 * 账本访问者接口
 * 两个方法是重载方法，就是在上面的元素类当中用到的，当然你也可以按照访问者模式类图当中的方式去做，
 * 将两个方法分别命名为viewConsumeBill和viewIncomeBill
 */
public interface Viewer extends Visitor {
    //查看消费的单子
    void view(ConsumeBill bill);

    //查看收入的单子
    void view(IncomeBill bill);
}
