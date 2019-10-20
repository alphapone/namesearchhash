# namesearchhash

It's a special hash function for fast search transliterated data in large tables


The hash function meet conditions:
* If two strings are cyrillic and latin representatiion of same string, function returns equal values for them
* If two strings are different record of same name, function returns equal values for them

----
So, for searching in large tables text in different (latin and cyrillic) form it is possible
use very fast hash join or sort merge as first stage of searching (```NameSearchHash.ruTranslitNameHash(a)==NameSearchHash.ruTranslitNameHash(b)```) and following
slow verification joined data, for exemple using regular expressions 
or set iof transliteration methods enumeration       

--------------
Example gratio of hash function using:

```
scala> NameSearchHash.ruTranslitNameHash("Ivanov Ivan")
res4: Long = 119278784

scala> NameSearchHash.ruTranslitNameHash("Ivan Ivanov")
res5: Long = 119278784

scala> NameSearchHash.ruTranslitNameHash("Ivan Ivanov")
res7: Long = 119278784

scala> NameSearchHash.ruTranslitNameHash("Иванов Иван")
res8: Long = 119278784

scala> NameSearchHash.ruTranslitNameHash("Иван Иванов")
res9: Long = 119278784

scala> NameSearchHash.ruTranslitNameHash("Nikolay Ivanitskiy")
res10: Long = -157418695

scala> NameSearchHash.ruTranslitNameHash("Nikolay Ivaniczkiy")
res11: Long = -157418695

scala> NameSearchHash.ruTranslitNameHash("Nikolay Ivanickiy")
res12: Long = -157418695

scala> NameSearchHash.ruTranslitNameHash("Nickolay Ivanickiy")
res13: Long = -157418695

scala> NameSearchHash.ruTranslitNameHash("Иваницкий НИколай")
res14: Long = -157418695
```

------------------

Hash function from this repository is purposed for russian language only and for cyrillic and latin representation. 
* If you need similar hash function for other languages or other encodings write please to inl@yandex.com.
* If you need to change licensee agreement condition for using in your work write please to inl@yandex.com.

## Test UI support
This library has UI for manual testing without scala REPL installed. So you can simple clone this repository run project
in IDEA and test provided by this project hash function in any string