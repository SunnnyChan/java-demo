/**
 * 适配器模式（Adapter）
 * 将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类能一起工作。
 *
 * 适配器模式分为类结构型模式和对象结构型模式两种，
 * 前者类之间的耦合度比后者高，且要求程序员了解现有组件库中的相关组件的内部结构，所以应用相对较少些。
 *
 * 优点：
 * 将目标类和适配者类解耦，通过引入一个适配器类来重用现有的适配者类，无须修改原有结构。
 * 增加了类的透明性和复用性，将具体的业务实现过程封装在适配者类中，对于客户端类而言是透明的，而且提高了适配者的复用性，
 *      同一个适配者类可以在多个不同的系统中复用。
 * 灵活性和扩展性都非常好，通过使用配置文件，可以很方便地更换适配器，也可以在不修改原有代码的基础上增加新的适配器类，完全符合“开闭原则”。
 *
 * 类适配器模式还有如下优点：
 * 由于适配器类是适配者类的子类，因此可以在适配器类中置换一些适配者的方法，使得适配器的灵活性更强。
 *
 * 对象适配器模式还有如下优点：
 * 一个对象适配器可以把多个不同的适配者适配到同一个目标
 * 可以适配一个适配者的子类，由于适配器和适配者之间是关联关系，根据“里氏代换原则”，适配者的子类也可通过该适配器进行适配。
 *
 * 缺点：
 * 对类适配器来说，更换适配器的实现过程比较复杂。
 *
 * 类适配器模式的缺点如下：
 * 对于Java、C#等不支持多重类继承的语言，一次最多只能适配一个适配者类，不能同时适配多个适配者
 * 适配者类不能为最终类，如在Java中不能为final类，C#中不能为sealed类
 * 在Java、C#等语言中，类适配器模式中的目标抽象类只能为接口，不能为类，其使用有一定的局限性
 *
 * 对象适配器模式的缺点如下：
 * 与类适配器模式相比，要在适配器中置换适配者类的某些方法比较麻烦。
 * 如果一定要置换掉适配者类的一个或多个方法，可以先做一个适配者类的子类，将适配者类的方法置换掉，
 * 然后再把适配者类的子类当做真正的适配者进行适配，实现过程较为复杂。
 *
 * 场景:
 * 以前开发的系统存在满足新系统功能需求的类，但其接口同新系统的接口不一致
 * 使用第三方提供的组件，但组件接口定义和自己要求的接口定义不同
 *
 * 应用：
 * Spring AOP、JPA、MVC中的适配器模式 https://blog.csdn.net/wwwdc1012/article/details/82780560
 *
 * 扩展:
 * 适配器模式（Adapter）可扩展为双向适配器模式，双向适配器类既可以把适配者接口转换成目标接口，也可以把目标接口转换成适配者接口
 */
package me.sunny.demo.dp.oop.structural.adapter;