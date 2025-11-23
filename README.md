# CapStore – Kotlin Jetpack Compose Mobile App

CapStore is a simple mobile e-commerce demo app created using **Kotlin** and **Jetpack Compose** as part of a Mobile Programming course.

## Features
- UI reproduced from Figma design  
- Navigation between multiple screens  
- TopBar & BottomBar implementation  
- Product list, product details & cart screen  
- Iterator Design Pattern implementation  
- Example of nullable variable usage  
- Clean structure following good practices  

## Iterator Pattern (Example)
```kotlin
class CapIterator(private val caps: List<Cap>) {
    private var index = 0
    fun hasNext() = index < caps.size
    fun next() = caps[index++]
}
