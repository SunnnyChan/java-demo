package me.sunny.demo.dp.oop.structural.adapter.voltage;

import java.util.LinkedList;
import java.util.List;

public class Client {
    private List<DC5> adapters = new LinkedList<DC5>();

    public Client() {
        this.adapters.add(new ChinaAdapter());
        this.adapters.add(new JapanAdapter());
    }

    public static void main(String[] args) {
        Client client = new Client();
        AC chinaAC = new AC220();
        DC5 adapter = client.getPowerAdapter(chinaAC);
        adapter.outputDC5V(chinaAC);

        // 去日本旅游，电压是 110V
        AC japanAC = new AC110();
        adapter = client.getPowerAdapter(japanAC);
        adapter.outputDC5V(japanAC);
    }

    // 根据电压找合适的变压器
    private DC5 getPowerAdapter(AC ac) {
        DC5 adapter = null;
        for (DC5 ad : this.adapters) {
            if (ad.support(ac)) {
                adapter = ad;
                break;
            }
        }
        if (adapter == null){
            throw new  IllegalArgumentException("没有找到合适的变压适配器");
        }
        return adapter;
    }
}
