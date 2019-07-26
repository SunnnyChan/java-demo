package me.sunny.demo.dp.behavioral.visitor.accoutbook;

import lombok.Getter;

public class IncomeBill implements Bill {
    @Getter
    private double amount;
    @Getter
    private String item;

    public IncomeBill(double amount, String item) {
        this.amount = amount;
        this.item = item;
    }

    public void accept(Viewer viewer) {
        viewer.view(this);
    }
}
