package org.bang.ap.first.app.lesson

// kotlin lambda
fun main() {
    // 普通类的成员方法声明与调用
    // 需要先构建出实例对象，才能访问成员方法
    // 实例对象的构建只需要在类名后面加上()
    // new Person()
    Person().test()

    // 静态类里面的方法都是静态方法
    // 不需要构建实例对象，可以通过类名，直接访问静态方法
    val double = NumUtil.double(1)
    println("double：$double")

    Person.test2()

    // 方法中的参数
    // 默认参数
    read(1, 2)
    // 具名参数
    read(start = 2)

    read2(1, 2, action = {
        // 方法体里面的最后一行，就是该方法的返回值
//        return@read2 "read2"
        val result = 1 * 2
        "括号内使用具名参数，传递action参数"
    })

    read2(start = 2) {
        "括号外传递action参数"
    }

    // 可变参数的要求
    // 只有一个参数可以标注为vararg
    // 如果vararg参数不是最后一个参数，可以使用具名参数语法，给后面的参数的传递值
    val append = append('1', '2', '3', '4', offset = 2)
    println("append：$append")

    val world = charArrayOf('w', 'o', 'r', 'l', 'd')
    val append2 = append('h', 'e', 'l', 'l', 'o', ' ', *world, offset = 2) // 使用*
    println("append2：$append2")

    // 调用具名参数类型的方法
    val numbers = arrayOf(1, 2, 3, 4)
    // 具名参数调用，即使用参数名action给它赋值
    transform(numbers, action = { index, element ->
        index * element // 闭包中最后一行表达式，计算后的结果，就是action函数的返回值
    })
    numbers.forEach {
        println("transform：$it")
    }
    transform(numbers) { index, element ->
        index * element
    }
    numbers.forEach {
        println("transform：$it")
    }

    // lambda隐形参数it
    numbers.forEach {
        println(it)
    }

    val list = arrayListOf(1, 2, 3, 4, 5, 6)
    list.forEachIndexed(action = { index: Int, element: Int ->
        println("forEachIndexed：$index -> $element")
    })
    list.forEachIndexed { index: Int, element: Int ->
        println("forEachIndexed：$index -> $element")
    }
}

// 普通类
class Person {
    fun test() {
        println("成员方法")
    }

    // 伴生类
    // 不需要构建实例对象，可以通过类名，直接访问静态方法
    // 当我们想在一个普通类里面，也就是class声明的中，想定义一个静态方法
    companion object {
        fun test2() {
            println("成员方法2")
        }
    }
}

// 静态类
object NumUtil {
    fun double(num: Int): Int {
        return num * 2
    }
}

fun read(offset: Int = 0, start: Int) {
    println("offset：$offset，start：$start")
}

// offset: Int 它有一个默认值是0
// start: Int 它没有默认值
// 第三个参数是一个方法，参数名叫做，action:()。如果参数的类型是()，说明该参数是一个方法类型
// 方法参数的返回值使用 -> Unit，Unit说明它不需要返回值
fun read2(offset: Int = 0, start: Int, action: () -> String) {
    val ret = action()
    println("read2：$ret")
}

fun append(vararg str: Char, offset: Int): String {
    val result = StringBuffer()
    for (char in str) {
        result.append(char)
    }
    return result.toString()
}

// lambda表达式作为方法中的参数的时候。定义transform方法，可以对数组中的元素进行变换
// array：要求传入一个数组，元素类型为Int整型
// action：要求传入的是一个方法，接受两个参数下标index，元素element。要求返回变换后的元素，会替换掉老的元素
fun transform(array: Array<Int>, action: (index: Int, element: Int) -> Int) {
    for (index in array.indices) {
        val newValue = action(index, array[index])
        array[index] = newValue
    }
}
