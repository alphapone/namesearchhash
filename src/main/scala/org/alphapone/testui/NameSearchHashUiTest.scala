package org.alphapone.testui

import java.awt.{BorderLayout, Dimension}

import javax.swing.JFrame
import javax.swing.JScrollPane
import javax.swing.JTextPane
import javax.swing.JButton
import javax.swing.JPanel
import java.awt.event.{ActionEvent, ActionListener}

import org.alphapone.util.NameSearchHash


/**
  * Test interface for NameSeachHash object
  */
object NameSearchHashUiTest extends App {

  val textPane = new JTextPane()
  val scrollPane = new JScrollPane(textPane)
  val toolBox = new JPanel()
  val actionCalcHashes = new JButton("Calc hashes")
  val actionAbout = new JButton("About")
  val actionLicensee = new JButton("Licensee")
  val actionHelp = new JButton("Help")
  val helpText = "Enter names here, one full name in each line, for example see below\nIvanov Ivan\nIvan Ivanov\nИванов Иван\nИван Иванов\nPetrov Nikolay\nPetrov Nickolay\nNikolai Petrov\nПетров Николай\n and press button \"Calc hashes\" to get hash values for each string "

  toolBox.add(actionCalcHashes)
  toolBox.add(actionAbout)
  toolBox.add(actionLicensee)
  toolBox.add(actionHelp)

  actionCalcHashes.addActionListener(new ActionListener {
    override def actionPerformed(actionEvent: ActionEvent): Unit = {
      textPane.setText(textPane.getText().split("\n").map(x=>x.split(":")(0)).map(x=>x + ": " + NameSearchHash.ruTranslitNameHash(x)).mkString("\n"))
    }
  })
  actionAbout.addActionListener(new ActionListener {
    override def actionPerformed(actionEvent: ActionEvent): Unit = {
      textPane.setText("# namesearchhash\n\nIt's a special hash function for fast search transliterated data in large tables\n\n\nThe hash function meet conditions:\n* If two strings are cyrillic and latin representatiion of same string, function returns equal values for them\n* If two strings are different record of same name, function returns equals values for them\n\n----\nSo, for searching in large tables text in different (latin and cyrillic) form it is possible\nuse very fast hash join or sort merge as first stage of searching (```NameSearchHash.ruTranslitNameHash(a)==NameSearchHash.ruTranslitNameHash(b)```) and following\nslow verification joined data, for exemple using regular expressions \nor set iof transliteration methods enumeration       \n\n--------------\nExample gratio of hash function using:\n\n```\nscala> NameSearchHash.ruTranslitNameHash(\"Ivanov Ivan\")\nres4: Long = 119278784\n\nscala> NameSearchHash.ruTranslitNameHash(\"Ivan Ivanov\")\nres5: Long = 119278784\n\nscala> NameSearchHash.ruTranslitNameHash(\"Ivan Ivanov\")\nres7: Long = 119278784\n\nscala> NameSearchHash.ruTranslitNameHash(\"Иванов Иван\")\nres8: Long = 119278784\n\nscala> NameSearchHash.ruTranslitNameHash(\"Иван Иванов\")\nres9: Long = 119278784\n\nscala> NameSearchHash.ruTranslitNameHash(\"Nikolay Ivanitskiy\")\nres10: Long = -157418695\n\nscala> NameSearchHash.ruTranslitNameHash(\"Nikolay Ivaniczkiy\")\nres11: Long = -157418695\n\nscala> NameSearchHash.ruTranslitNameHash(\"Nikolay Ivanickiy\")\nres12: Long = -157418695\n\nscala> NameSearchHash.ruTranslitNameHash(\"Nickolay Ivanickiy\")\nres13: Long = -157418695\n\nscala> NameSearchHash.ruTranslitNameHash(\"Иваницкий НИколай\")\nres14: Long = -157418695\n```\n\n------------------\n\nHash function from this repository is purposed for russian language only and for cyrillic and latin representation. \n* If you need similar hash function for other languages or other encodings write please to inl@yandex.com.\n* If you need to change licensee agreement condition for using in your work write please to inl@yandex.com.\n")
    }
  })
  actionLicensee.addActionListener(new ActionListener {
    override def actionPerformed(actionEvent: ActionEvent): Unit = {
      textPane.setText("# Limited Free Public License 1.0.1+\n\nPermission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted anybody \nexcept of following organizations:\n\n* AT Consulting,\n* DPD, \n* Diasoft, \n* MMVB (moex.com),\n* USA Government.\n\nAnybody who uses code and methods from this repository should put above restriction in licensee agreement for derived work.  \n\n\nTHE SOFTWARE IS PROVIDED \"AS IS\" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR\nANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS\nACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.")
    }
  })
  actionHelp.addActionListener(new ActionListener {
    override def actionPerformed(actionEvent: ActionEvent): Unit = {
      textPane.setText(helpText)
    }
  })

  textPane.setText(helpText)

  val frame = new JFrame("NameSearchHashUiTest")

  frame.getContentPane.add(toolBox, BorderLayout.NORTH)
  frame.getContentPane.add(scrollPane, BorderLayout.CENTER)

  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  frame.setSize(new Dimension(600, 800))
  frame.setLocationRelativeTo(null)

  frame.setVisible(true)

}
