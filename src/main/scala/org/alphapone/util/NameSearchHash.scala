package org.alphapone.util

object NameSearchHash {
  /**
    * A hash function meet conditions:
    *    1. If two strings are cyrillic and latin representatiion of same string, function returns equal values for them
    *    2. If two strings are different record of same name, function returns equals values for them
    * @param s - string in cyrillic of in latin representation
    * @return specific hash value for string
    */
  def ruTranslitNameHash(s:String):Long = {
    s.toLowerCase().split(" ").sorted(Ordering.String).mkString(" ")
      .toCharArray().map(
      x => x match {
        case 'е' => 'Е'
        case 'ё' => 'Е'
        case 'y' => 'Е'
        case 'я' => 'Е'
        case 'e' => 'Е'
        case 'o' => 'Е'
        case 'о' => 'Е'
        case 'а' => 'Е'
        case 'э' => 'Е'
        case 'ю' => 'Е'
        case 'ы' => 'Е'
        case 'и' => 'Е'
        case 'й' => 'Е'
        case 'i' => 'Е'
        case 'j' => 'Е'
        case '`' => '~'
        case '\'' => '~'
        case 'ь' => '~'
        case 'ъ' => '~'
        case 'a' => 'Е'
        case 'ж' => 'Ж'
        case 'z' => 'Ж'
        case 'h' => 'Ж'
        case 'х' => 'Ж'
        case 'k' => 'Ж'
        case 'к' => 'Ж'
        case 'ц' => 'Ж'
        case 'c' => 'Ж'
        case 'с' => 'Ж'
        case 't' => 'Ж'
        case 'т' => 'Ж'
        case 's' => 'Ж'
        case 'ч' => 'Ж'
        case 'ш' => 'Ж'
        case 'щ' => 'Ж'
        case 'б' => 'Б'
        case 'b' => 'Б'
        case 'в' => 'В'
        case 'v' => 'В'
        case 'г'  => 'Г'
        case 'g' => 'Г'
        case 'д'  => 'Д'
        case 'd' => 'Д'
        case 'з'  => 'Ж'
        case 'л'  => 'Л'
        case 'l' => 'Л'
        case 'м'  => 'М'
        case 'm' => 'М'
        case 'н'  => 'Н'
        case 'n' => 'Н'
        case 'р'  => 'Р'
        case 'r' => 'Р'
        case 'у'  => 'У'
        case 'u' => 'У'
        case 'ф'  => 'Ф'
        case 'f' => 'Ф'
        case default => '~'
      }
    ).foldLeft(List[Char]()) {
      case (acc,i) if (acc.size>0 && acc(0).equals(i)) => acc
      case (acc,i) if (i=='~') => acc
      case (acc,i) => i::acc
    }.mkString("").split(" ").sorted(Ordering.String).mkString(" ").toCharArray.foldLeft(7) {
      case (acc,i)=>acc*31 + i.toInt
    }
  }
}
