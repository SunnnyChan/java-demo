package me.sunny.demo.dp.oop.behavioral.visitor.accoutbook;

import me.sunny.demo.dp.oop.behavioral.visitor.role.Node;

/**
 * 单个账单的接口
 */
public interface Bill extends Node {
    void accept(Viewer viewer);
}
