package rws

import answers.rws.StateAnswers
import exercises.rws.BathroomSecurity._
import exercises.rws.BathroomSecurity.Instruction._
import exercises.rws.BathroomSecurity.KeyPad._
import exercises.rws.StateExercises
import org.scalatest.{FunSuite, Matchers}
import org.typelevel.discipline.scalatest.Discipline
import toimpl.rws.StateToImpl
import cats.implicits._

class StateAnswersTest extends StateTest(StateAnswers)
class StateExercisesTest extends StateTest(StateExercises)

class StateTest(impl: StateToImpl) extends FunSuite with Discipline with Matchers {
  import impl._

  test("findCode") {
    findCode(
          startingPoint = K5,
          instructionLines = List(
            List(Up, Left, Left),
            List(Right, Right, Down, Down, Down),
            List(Left, Up, Right, Down, Left),
            List(Up, Up, Up, Up, Up, Down)
          )
     ) shouldEqual List(K1, K9, K8, K5)
  }

  test("findCode long"){
    val instructionStr =
      """|LLLRLLULLDDLDUDRDDURLDDRDLRDDRUULRULLLDLUURUUUDLUUDLRUDLDUDURRLDRRRUULUURLUDRURULRLRLRRUULRUUUDRRDDRLLLDDLLUDDDLLRLLULULRRURRRLDRLDLLRURDULLDULRUURLRUDRURLRRDLLDDURLDDLUDLRLUURDRDRDDUURDDLDDDRUDULDLRDRDDURDLUDDDRUDLUDLULULRUURLRUUUDDRLDULLLUDLULDUUDLDLRRLLLRLDUDRUULDLDRDLRRDLDLULUUDRRUDDDRDLRLDLRDUDRULDRDURRUULLUDURURUUDRDRLRRDRRDRDDDDLLRURULDURDLUDLUULDDLLLDULUUUULDUDRDURLURDLDDLDDUULRLUUDLDRUDRURURRDDLURURDRLRLUUUURLLRR
         |UUUUURRRURLLRRDRLLDUUUUDDDRLRRDRUULDUURURDRLLRRRDRLLUDURUDLDURURRLUDLLLDRDUDRDRLDRUDUDDUULLUULLDUDUDDRDUUUDLULUDUULLUUULURRUDUULDUDDRDURRLDDURLRDLULDDRUDUDRDULLRLRLLUUDDURLUUDLRUUDDLLRUURDUDLLDRURLDURDLRDUUDLRLLRLRURRUDRRLRDRURRRUULLUDLDURDLDDDUUDRUUUDULLLRDRRDRLURDDRUUUDRRUUDLUDDDRRRRRLRLDLLDDLRDURRURLLLULURULLULRLLDDLDRLDULLDLDDDRLUDDDUDUDRRLRDLLDULULRLRURDLUDDLRUDRLUURRURDURDRRDRULUDURRLULUURDRLDLRUDLUDRURLUDUUULRRLRRRULRRRLRLRLULULDRUUDLRLLRLLLURUUDLUDLRURUDRRLDLLULUDRUDRLLLRLLDLLDUDRRURRLDLUUUURDDDUURLLRRDRUUURRRDRUDLLULDLLDLUDRRDLLDDLDURLLDLLDLLLDR
         |LRDULUUUDLRUUUDURUUULLURDRURDRRDDDLRLRUULDLRRUDDLLUURLDRLLRUULLUDLUDUDRDRDLUUDULLLLRDDUDRRRURLRDDLRLDRLULLLRUUULURDDLLLLRURUUDDDLDUDDDDLLLURLUUUURLRUDRRLLLUUULRDUURDLRDDDUDLLRDULURURUULUDLLRRURDLUULUUDULLUDUUDURLRULRLLDLUULLRRUDDULRULDURRLRRLULLLRRDLLDDLDUDDDUDLRUURUDUUUDDLRRDLRUDRLLRDRDLURRLUDUULDRRUDRRUDLLLLRURRRRRUULULLLRDRDUDRDDURDLDDUURRURLDRRUDLRLLRRURULUUDDDLLLRDRLULLDLDDULDLUUDRURULLDLLLLDRLRRLURLRULRDLLULUDRDR
         |RURRRUDLURRURLURDDRULLDRDRDRRULRRDLDDLDUUURUULLRRDRLDRRDRULLURRRULLLDULDDDDLULRUULRURUDURDUDRLRULLLRDURDDUDDRDLURRURUURDLDDDDDURURRURLLLDDLDRRDUDDLLLDRRLDDUUULDLLDRUURUDDRRLDUULRRDDUDRUULRLDLRLRUURLLDRDLDRLURULDLULDRULURLLRRLLDDDURLRUURUULULRLLLULUDULUUULDRURUDDDUUDDRDUDUDRDLLLRDULRLDLRRDRRLRDLDDULULRLRUUDDUDRRLUDRDUUUDRLLLRRLRUDRRLRUUDDLDURLDRRRUDRRDUDDLRDDLULLDLURLUUDLUDLUDLDRRLRRRULDRLRDUURLUULRDURUDUUDDURDDLRRRLUUUDURULRURLDRURULDDUDDLUDLDLURDDRRDDUDUUURLDLRDDLDULDULDDDLDRDDLUURDULLUDRRRULRLDDLRDRLRURLULLLDULLUUDURLDDULRRDDUULDRLDLULRRDULUDUUURUURDDDRULRLRDLRRURR
         |UDDDRLDRDULDRLRDUDDLDLLDDLUUURDDDLUDRDUDLDURLUURUDUULUUULDUURLULLRLUDLLURUUUULRLRLLLRRLULLDRUULURRLLUDUDURULLLRRRRLRUULLRDRDRRDDLUDRRUULUDRUULRDLRDRRLRRDRRRLULRULUURRRULLRRRURUDUURRLLDDDUDDULUULRURUDUDUDRLDLUULUDDLLLLDRLLRLDULLLRLLDLUUDURDLLRURUUDDDDLLUDDRLUUDUDRDRLLURURLURRDLDDDULUURURURRLUUDUDLDLDDULLURUDLRLDLRLDLDUDULURDUDRLURRRULLDDDRDRURDDLDLULUDRUULDLULRDUUURLULDRRULLUDLDRLRDDUDURRRURRLRDUULURUUDLULDLRUUULUDRDRRUDUDULLDDRLRDLURDLRLUURDRUDRDRUDLULRUDDRDLLLRLURRURRLDDDUDDLRDRRRULLUUDULURDLDRDDDLDURRLRRDLLDDLULULRRDUDUUDUULRDRRDURDDDDUUDDLUDDUULDRDDULLUUUURRRUUURRULDRRDURRLULLDU""".stripMargin

    val instructionLines = instructionStr.lines.toList.traverse(_.toList.traverse(parseInstruction)).getOrElse(sys.error("invalid instructions"))

    findCode(
      startingPoint = K5,
      instructionLines = instructionLines
    ) shouldEqual List(K3, K8, K9, K6, K1)
  }
}