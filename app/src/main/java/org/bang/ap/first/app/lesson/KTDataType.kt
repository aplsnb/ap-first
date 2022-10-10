package org.bang.ap.first.app.lesson

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
    println("doubleNumber：$doubleNumber")
    println("floatNumber：$floatNumber")

    // 字符类型
    // 字符类型的声明用单引号
    val char: Char = '0'

    // 布尔类型，使用Boolean来声明类型，只有两种值true/false
    val isVisible: Boolean = false
    val isVisible2 = true

    // 字符串类型
    val str: String = "1234567890" // 从0开始
    // 字符串的取值
    val strNumber2: Char = str[1]
    // 字符串的模板表达式以$开始
    println("The Result：$str")
    println("Text length is ${str.length}")
    // 字符串的拼接操作
    println("I am " + 10 + " years old！")
    // 字符转义
    val helloWorld = "Hello，World！\n"
    println("$helloWorld\nhow are you")

    // helloWorld3要求它的字符串内容是json格式
    val helloWorld3 = "{\"key\" : \"value\"}"
    // 分界符，内部不需要转义，看起来更清爽
    val helloWorld4 = """
        {"key" : "value"}
    """.trimIndent()

    // 数据类型间的强制转换
    val number100 = 100 // 声明一个整型number对象
    println("转换成string：$number100")
    println("转换成long：${number100.toLong()}")

    // 数据类型的加减乘除
    val numberInt: Int = 3 / 2 // = 1.5
    println("numberInt：$numberInt") // 输出1

    // 整数的3除以小数2
    val numberDouble: Double = 3 / 2.toDouble() // 输出1.5
    println("numberDouble：$numberDouble")
    println("乘法：" + numberInt * numberInt)
    println("取余：" + 3 % 2)

    // 位运算
    val vip = true
    val admin = false
    // 与操作，要求两个条件都满足结果才会为true
    val result = vip.and(admin) // false
    // 或操作，只要有一个条件为true，结果就为true
    val result2 = vip.or(admin)

    // 无符号右移
    // 0000 1000 ---> 0000 0010 = 2
    val result3 = 8 ushr (2)
    println("result：$result")
    println("result2：$result2")
    println("result3：$result3")
}
