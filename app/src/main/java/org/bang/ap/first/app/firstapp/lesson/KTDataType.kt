package org.bang.ap.first.app.firstapp.lesson

// kotlin数据类型
fun main() {
    // 1. 如何声明一个基础数据类型的变量，有哪些方式？
    // 基础数据类型的整型默认数据类型均为Int，如果超过了Int的取值范围，则会推断为Long
    val number = 100 // 编辑器会根据赋值【100】，推断变量number数据类型为Int

    // 虽然没有明确指定变量bigNumber的数据类型，但是编辑器会根据赋值发现【8000000000】已经超过了Int的最大值
    // 所以bigNumber的数据类型会升级为Long
    val bigNumber = 8000000000

    // 在赋值数字的后面加上L，会自动推断为Long类型
    val longNumber = 20L

    // 在变量后面加上：明确数据类型
    val byteNumber: Byte = 1

    // 浮点类型
    // 编辑器同样会根据赋值推断变量的类型
    val doubleNumber: Double = 3.14159268888
    val floatNumber: Float = 3.14159268888f // 尾部加f或F显式表示这是一个Float类型的浮点数
    println("doubleNumber：${doubleNumber}")
    println("floatNumber：${floatNumber}")
}
