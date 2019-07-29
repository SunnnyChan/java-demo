package me.sunny.demo.dp.oop.behavioral.visitor.accoutbook;

public class Client {
    public static void main(String[] ags) {
        AccountBook accountBook = new AccountBook();

        //添加两条收入
        accountBook.addBill(new IncomeBill(10000, "卖商品"));
        accountBook.addBill(new IncomeBill(12000, "卖广告位"));
        //添加两条支出
        accountBook.addBill(new ConsumeBill(1000, "工资"));
        accountBook.addBill(new ConsumeBill(2000, "材料费"));

        Viewer boss = new Boss();
        Viewer cpa = new CPA();

        // 两个访问者分别访问账本
        accountBook.show(cpa);
        accountBook.show(boss);

        // 访问者查看账本后获得需要的结果
        ((Boss) boss).getTotalConsume();
        ((Boss) boss).getTotalIncome();
    }
}
