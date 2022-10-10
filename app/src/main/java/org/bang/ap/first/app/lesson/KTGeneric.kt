package org.bang.ap.first.app.lesson

import com.google.gson.Gson
import org.json.JSONObject

// kotlin泛型
fun main() {
    // 1. 泛型接口
    DrinkApple().drink("drink")

    // 2. 泛型类
    BlueColor("blue").printColor()

    // 3. 泛型方法
    fromJson("{}", String::class.java)

    // 泛型约束
    fromJson2("{}", Gson::class.java)

    // 泛型约束
    fromJson3("{}", User::class.java)
}

interface Drink<T> {
    fun drink(t: T /* 泛型字段 */)
}

class DrinkApple : Drink<String> {
    override fun drink(t: String) {
        println("drink: $t")
    }
}

abstract class Color<T>(val t: T /* 泛型字段 */) {
    abstract fun printColor()
}

class BlueColor(private val color: String) : Color<String>(color) {
    override fun printColor() {
        println("BlueColor: $color")
    }
}

fun <T> fromJson(json: String, tClass: Class<T>): T? {
    return tClass.newInstance()
}

// 泛型类型限定-1
// 所传递的类型T必须满足时Gson的子类或Gson类
fun <T : Gson> fromJson2(json: String, tClass: Class<T>): T? {
    return tClass.newInstance()
}

// 泛型类型限定-2
// 所传递的类型T必须同时满足where子句的所有条件
// 在下面的示例中，类型T必须既实现了JSONObject，也实现了Comparable
fun <T> fromJson3(json: String, tClass: Class<T>): T? where T : JSONObject, T : Comparable<T> {
    return tClass.newInstance()
}

class User : JSONObject(), Comparable<User> {
    override fun compareTo(other: User): Int {
        return 0
    }
}

open class Animal

open class DogAnimal : Animal()

class CatAnimal : Animal()

class WhiteDogAnimal : DogAnimal()

fun animalFuns() {
    val animal: Animal = DogAnimal()
//    val animalList: ArrayList<Animal> = ArrayList<DogAnimal>()

    // 传入的泛型参数可以是Animal的子类DogAnimal，CarAnimal，WhiteDogAnimal
    // kotlin不支持集合泛型转换，需用out
    // 1. 使用处使用out关键字声明泛型上限
    val animalList: ArrayList<out Animal> = ArrayList<DogAnimal>()
    val animalList2: ArrayList<Animal> = ArrayList<DogAnimal>()

    // 使用处使用in关键字声明，约定泛型的下限，允许传入的泛型类型是DogAnimal及其父类Animal
    val animalList3: InArrayList<in DogAnimal> = InArrayList<Animal>()
    val animalList4: InArrayList<DogAnimal> = InArrayList<Animal>()
}

// 在定义处使用out关键字声明，允许传入的泛型参数可以是T以及T的子类
class ArrayList<out T>

// 在定义处使用in关键字声明，允许传入的泛型参数可以是T以及T的父类
class InArrayList<in T>
