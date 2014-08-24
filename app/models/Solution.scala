package models

case class Solution(publicId: String,
  title: String,
  description: String,
  YouTubeId: String,
  grade: Int,
  event: String)

object Solution {
  var solutions = Set(
    Solution("amo40-g05-p01", "40.AMO, 5.klases 1.uzdevums",
      """Cik reizes diennaktī sakrīt pulksteņa stundu un minūšu
rādītāji? (Plkst. 00:00 un 24:00 ieskaitīt vienu reizi.) 
<i>Atbildi pamatot!</i>""",
      "UNyM7DyFDug", 5, "amo40"),
    Solution("amo40-g05-p02", "40.AMO, 5.klases 2.uzdevums",
      """24-stāvu mājā ir lifts, kuram ir divas pogas. 
Nospiežot vienu pogu,
tas paceļas (ja iespējams) 17 stāvus uz augšu, nospiežot otru –
nolaižas 8 stāvus uz leju (ja iespējams). Noskaidro, no kura stāva
ar šo liftu var nokļūt uz jebkuru citu
stāvu šajā mājā. (Lifts nevar uzbraukt
augstāk par 24. stāvu un zemāk par 1.
stāvu.)""", "96mCTGXI4N4", 5, "amo40"),
    Solution("amo40-g05-p03", "40.AMO, 5.klases 3.uzdevums",
      """1. zīmējumā katrā aplītī ierakstīt vienu
ciparu, katrā aplītī – citu, tā, lai katros
trīs aplīšos, kas atrodas uz vienas
taisnes, ierakstīto skaitļu summa būtu
viena un tā pati.<br />
<img src="amo40-fig01.png" alt="1.zīm."/>""",
      "7o9b9yKO08c", 5, "amo40"),
    Solution("amo40-g05-p04", "40.AMO, 5.klases 4.uzdevums",
      """No 2. zīmējumā redzamajām figūrām salikt
taisnstūri ar laukumu 40 rūtiņas. Figūras nedrīkst
pārklāties un katra veida figūra jāizmanto vismaz
vienu reizi. (Figūras var būt pagrieztas vai
apgrieztas otrādi.)<br />
<img src="amo40-fig02.png" alt="2.zīm."/>""",
      "aPtiwtx9Jkg", 5, "amo40"),
    Solution("amo40-g05-p05", "40.AMO, 5.klases 5.uzdevums",
      """Kuba katra skaldne sadalīta četros vienādos kvadrātos. Vai šos
kvadrātus var nokrāsot a) divās; b) trīs krāsās tā, ka kvadrāti, kam
ir kopīga mala, ir nokrāsoti dažādās krāsās? Katrs kvadrāts pilnībā
ir jākrāso vienā krāsā. <i>Atbildi pamatot!</i>""",
      "YQAQ3e3cfqo", 5, "amo40"),
    Solution("amo40-g06-p01", "40.AMO, 6.klases 1.uzdevums",
      """Uz tāfeles uzrakstīti desmit skaitļi:<br />
1 2 3 4 5 6 7 8 9 10.<br />
Alfons nodzēš jebkurus divus no tiem (apzīmēsim tos ar a un b)
un to vietā uzraksta skaitli, kas vienāds ar  a+b+2. Šo operāciju
viņš atkārto, kamēr uz tāfeles paliek viens skaitlis.
Pamato, ka neatkarīgi no secības, kādā Alfons izpilda darbības,
beigās tiek iegūts viens un tas pats skaitlis. Kāds tas ir?""",
      "VAqkROCPuKk", 5, "amo40"))
  def findAll = solutions.toList.sortBy(_.publicId)

  def shortenDescription(desc: String): String = {
    val eltDescription = desc.trim 
    val eltDescription1 = eltDescription.replaceAll("""(?m)\s+""", " ")
    val eltDescription15 = eltDescription1.replaceAll("""(?m)<img [^<>]+alt="([^<>"]+)"[^<>]+>""", "[$1]")
    val eltDescription2 = eltDescription15.replaceAll("""(?m)<[^<>]+>""", "")
    if (eltDescription2.length() > 100) {
      eltDescription2.substring(0, 97) + "..."
    } else {
      eltDescription2
    }
  }
}