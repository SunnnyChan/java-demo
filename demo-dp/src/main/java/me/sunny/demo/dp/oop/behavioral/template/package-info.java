/**
 * 模板方法模式 (Template Method Pattern) - 封装算法
 *
 * 实现思路是，创建一个桩方法，并且定义一些步骤让子类来实现。
 * 模板方法定义了一个算法的执行步骤，或者说能够提供一种默认的实现，这种实现概括一部分子类或者全部子类的共同部分。
 *
 * ## 实现
 * 为了确保子类不能重写(override)模板方法，应当使用final。
 */
package me.sunny.demo.dp.oop.behavioral.template;