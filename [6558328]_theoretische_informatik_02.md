# Theoretische Informatik II

## Sortieren mit Merge Sort


Beim Merge Sort werden zwei Teilarrays durch sortiertes Mischen zusammengeführt. Der Algorithmus teilt dabei den Array solange auf bis nur noch pro Teilarray maximal 1 Element übrig ist. Diese Teilarrays werden zusammengeführt indem immer die ersten beiden Elemente zweier Teilarrays verglichen werden. Ist ein Wert kleiner  so wird er in den Ausgabearray geschrieben und man betrachtet im nächsten Vergleich das darauf folgende Element. Dies wird so lange durchgeführt bis man das Ende beider Teilarrays erreicht ist. Die gegebenen Teilarrays müssen bereits geordnet sein, weshalb die zuvor angesprochene Teilung bis zu Arrays mit maximal einem Element notwendig ist, da Listen mit einem Element automatisch sortiert sind.
> Der Merge Sort Algorithmus basiert auf dem *Teile-und-Herrsche-Paradigma* (vgl. Sedgewick et al, 2014).


Charakteristika(N = Größe des Arrays):
* Laufzeitverhalten: N log(N)
* zusätzlich benötigter Speicherplatz: N


Einfaches Sortieren mit Mergesort

||   8 1 2 6 3 5 9 7 4  |  |   | | |
|----| --- | ----| ---| ---|---|
| 8 1 2 6 || 3 5 9 7 4 |||Array wird in zwei neue Arrays gespeichert|
| 8 1 | 2 6 | 3 5 | 9 7 4||
|1 8 | 2 6 | 3 5 | 9 7 | 4 |Erstes Sortieren|
|1 8 | 2 6 | 3 5 | 7 9 | 4 |Maximale Teilung
|1 2 6 8 || 3 5 | 4 7 9 ||Erstes Mergen 
|1 2 6 8 || 3 4 5 7 9 |
||1 2 3 4 5 6 7 8 9 |

![Sortieren Grafisch](/Mergesort.jpg "Graphische Darstellung")

Einfacher Mergesort in Java

```Java
public static Comparable[] merge(Comparable[] input){
	Comparable[] part1 = new Comparable[input.length/2];
	Comparable[] part2 = new Comparable[input.length/2];
	if(input.length > 2){
		System.arraycopy(input, 0, part1, 0, part1.length);
		System.arraycopy(input, part1.length, part2, 0, part2.length);
		part1 = merge(part1);
		part2 = merge(part2);
	}else{
		part1 = new Comparable[1];
		part2 = new Comparable[1];
		part1[0] = input[0];
		part2[0] = input[1];
	}
	int left = 0;
	int right = 0;
	int out = 0;
	Comparable[] output = new Comparable[input.length];
	if(part1.length == 1 && part2.length == 1){
		int result = part1[left].compareTo(part2[right]);
		if(result <= 0){
			output[0] = part2[0];
			output[1] = part1[0];
		} else {
			output[0] = part1[0];
			output[1] = part2[0];
		}
		return output;
	}
	while(left < part1.length || right < part2.length){
		int result = part1[left].compareTo(part2[right]);
		if( result == 0){
			if( part1[left] == null && part2[right] == null){
				break;
			}
				output[out] = part1[left];
				out++;
				output[out] = part2[right];
				out++;
				left++;
				right++;
		}
		else if(result < 0){
			output[out] = part2[right];
			right++;
			out++;
		}else{
			output[out] = part1[left];
			left++;
			out++;
		}
	}
	return output;
}
```

---
### Abstraktes In-Place-Mergen


Beim In-Place-Verfahren versucht man den benötigten Speicher bei den rekursiven Methodenaufrufen so gering wie möglich zu halten.
Dabei wird der Array im zu sortierenden Array selbst sortiert und somit einiges an Speicher eingespart


Die Sortierung wird über eine Funktionen realisiert die jeweils den niedrigsten, den höchsten und den mittleren Index des zu sortierenden Teils des Arrays und den gesamten Array selbst übergeben bekommen. Dieser Abschnitt wird dann iterativ per merge sortiert


---
### Top-Down-Mergesort


Beim Top-Down-Mergesort wird ein weiterer Sortieralgorithmus (z.B. Insertionsort oder Selectionsort) zur Hilfe genommen. Die weiteren Sortieralgorithmen werden für das Sortieren kleinerer Teilarrays genutzt, da z.B. der Insertionsort bei kleinen Arrays effizienter ist als der Mergesort.
Das bedeutet, dass die Aufteilung des Arrays, wie sie beim Mergesort vorgesehen ist, nur bis zu einem gewissen Grad durchgeführt wird und dann die restliche Sortierung der Arrayfragmente durch einen für kleine Arrays effizienteren Algorithmus durchgeführt wird.


>Der Top-Down-Mergesort Algorithmus hat ein Laufzeit verhalten zwischen 1/2 N log(N) und N log(N). 


---
### Bottom-Up-Mergesort


Der Bottom-Up-Mergesort verzichtet auf Rekursion. Der Algorithmus iteriert über den Array wobei er beim ersten Durchlauf je zwei aufeinander folgende Elemente sortiert. Die entstehenden Gruppen werden dann beim nächsten Durchlauf wieder mit je zu zweit Gruppe in eine neue Gruppe gemerget. Dieser Prozess läuft so lange bis der Array sortiert ist. Die Anzahl der benötigten Vergleiche ist gleich wie bei der rekursiven Variante, jedoch kann es beim Bottom-Up-Mergesort, passieren, dass ein Element lange Zeit als eigene Gruppe betrachtet wird und erst bei der vorletzten Iteration mit einer sehr großen Gruppe gemergt. 


---
---
## Bäume

---
### Binärer Suchbaum


Beim binären Suchbaum hat jeder Knoten maximal 2 Kindknoten, wobei das linke immer den kleineren Wert hat und der rechte immer den größeren Wert. Alle Knoten links eines Knotens sind kleiner als der Ausgangsknoten und alle Knoten rechts sind größer. Dadurch ist die maximale Zahl der Vergleiche bei der Suche in einem binären Suchbaum gleich dessen Höhe ist. Auf jeden Knoten referenziert genau ein Elternknoten, außer beim Wurzelknoten auf den kein anderer Knoten referenziert. 

![Binärer-Suchbaum-Vollständig](/Binärer_Suchbaum.png "Binärer Suchbaum")

Beim Einfügen eines Knoten wird dieser in der Regel als ein Blatt des Baumes eingefügt, da beim binären Suchbaum in der Regel die Struktur beim Einfügen nicht optimiert wird. Dabei wird der einzufügende Knoten mit der Wurzel verglichen. Ist der Schlüssel des neuen Knoten größer als der des Knotens so wird er an den rechten Kindknoten weitergegeben. Dieser Vorgang wird solange wiederholt bis er nicht mehr weitergegeben werden kann, also keine weiterer Knoten da ist. Dann wird er als Kindknoten des letzten Vergleichsknoten eingefügt.

Einfügen des Elements 10:
![Einfügen-Binärbaum](/einfügen-binärbaum.png "Einfügen eines Elements in einen binären Suchbaum")

Das Löschen eines Knoten muss in mehrere Fälle unterschieden werden. Ist der zu entfernente Knoten ein Blatt des Baumes so muss lediglich dessen Referenz gelöscht werden. Hat der zu löschende Knoten genau ein Kindknoten so wird dieser als Referenz an den Elternknoten des zu löschenden Knotens gegeben. Hat der zu entfernende Knoten zwei Kindknoten/Teilbäume nimmt immer der Knoten den Platz des zu entfernenden Knotens ein der der kleinste des rechten Teilbaums ist.


---

### AVL-Bäume


Der AVL-Baum ist besonders gut geeignet, wenn häufig schnell auf Daten zugegriffen werden muss.
Der AVL-Baum ist ein binärer Suchbaum, bei dem per Rotation beim Einfügen dafür gesorgt wird, dass er balanciert bleibt und dadurch maximal log N Schlüssel verglichen werden müssen, um ein Objekt zu finden. 
Damit der Baum balanciert bleibt wird bei dem Einfügen mithilfe des Balance-Faktors rotiert, wodurch die balancierte Struktur des binären Suchbaums erhalten bleibt.
Der Balance-Faktor kann für jeden Knoten des Baumes berechnet werden. Dabei wird die Höhe des linken Teilbaums des Knotens von der Höhe des rechten Teilbaums subtrahiert.


> BF(t) := Height(tr) - Height(tl)

Ein binärer Suchbaum ist dann ein AVL-Baum, wenn der Balance-Faktor jedes Knotens kleiner gleich 1 und größer gleich -1 ist.

> -1 <= BF(t) <= 1

Ist der Balance-Faktor eines Knoten negativ so ist der linke Teilbaum höher als der rechte.

![AVL-Baum-Vollständig](/AVL-Baum.png "AVL-Baum")

Um aus eine binären Suchbaum einen AVL-Baum zu machen, wird die Rotation bei allen Knoten mit | BF(t) | >= 2 eingesetzt bis alle Knoten einen Balance-Faktor zwischen 1 und -1 haben.

Beim Einfügen wird zuerst wie im binären Suchbaum der Knoten eingefügt, danach wird der Baum ggf. wieder zu einem AVL-Baum umgewandelt. Dabei wird der Balance-Faktor für alle betroffene Knoten berechnet. Wurde in einem linken Teilbaum ein linker Kindknoten angelegt wird die einfache Rechtsrotation durchgeführt, wird in einem rechten Teilbaum ein rechtes Kind eingefügt wird die einfache Linksrotation durchgeführt.

Übersicht AVL-Baum Rotationen

| Rotation                     |      Balance-Faktor oberer Knoten       |         Balance-Faktor unterer Knoten        |
| ---------------------------- | --------------------------------------- | -------------------------------------------- |
| Rechts-Rotation              |                   -2                    |                     -1                       |
| Links-Rotation               |                    2                    |                      1                       |
| Links-Rechts-Rotation        |                   -2                    |                      1                       |
| Rechts-Links-Rotation        |                    2                    |                     -1                       |

#### **Einfügen mit Rechts-Rotation**

Wird ein Knoten in einem linken Teilbaum als linker Kindknoten eingefügt und ein Knoten des Teilbaums verletzt dadurch das Balance-Kriterium muss eine einfache Rechts-Rotation durchgeführt werden. Dabei wird der das Balance-Kriterium verletzende Knoten zum rechten Kindknoten des eigenen linken Kindknotens. Die neue Wurzel des Teilbaums hat nun rechts den zuvorigen Elternknoten und links den zuvorigen linken Kindknoten. Der ehemalige Elternknoten hat jetzt links den vorigen rechten Kindknoten der Wurzel des Teilbaums. Abschließend wird die Refernz auf die neue Wurzel des Teilbaums zum nächsthöheren Knoten weitergegeben und gespeichert.

![Rechts-Rotation](/Rechts-Rotation.png "Rechts-Rotation exemplarisch")



#### **Einfügen mit Links-Rotation**

Die Links-Rotation ist die Invertierung der Rechts-Rotation. Die Links-Rotation kann genutzt werden, wenn bei einem rechten Teilbaum ein rechter Kindknoten eingefügt wird und ein Knoten gegen das Balance-Kriterium verstößt. 

![Links-Rotation](/Links-Rotation.png "Links-Rotation exemplarisch")



#### **Einfügen mit Links-Rechts-Rotation**

Bei einer Rechts-Links-Rotation wird zuerst eine Rechts-Rotation durchgeführt welche von einer Links-Rotation gefolgt ist. Diese wird vor allem benötigt wenn in den linken Teilbaum ein rechter Kindknoten eingefügt wird, der dafür sorgt, dass einer höherer Knoten das Balance-Kriterium verletzt. 

![Links-Rechts-Rotation](/Links-Rechts-Rotation.png "Links-Rechts-Rotation exemplarisch")



#### **Einfügen mit Rechts-Links-Rotation**

Die Rechts-Links-Rotation ist die Invertierung der der Links-Rechts-Rotation. Sie wird angewandt wenn ein in einen rechten Teilbaum ein linker Kindknoten eingefügt wird und dadurch ein Knoten das Balance-Kriterium verletzt.

![Rechts-Links-Rotation](/Rechts-Links-Rotation.png "Rechts-Links-Rotation exemplarisch")

---
---


## Hashing


### Hashfunktionen

Hashfunktionen bilden mit Eingaben einen Hashwert der, je nach angewendeter Funktion, nahezu eindeutig ist.

### Hashtabelle

Für jeden Wert wird, über eine Hashfunktion, ein Index in der Tabelle berechnet an dem dieser abgelegt werden soll. Je nach gewählter Funktion kann es zu sog. Kollisionen kommen bei denen zwei Eingabewerte den gleichen Hashwert haben. Tritt eine solche Kollision ein wird eine Ausweichstrategie, wie zum Beispiel Verkettung oder lineare Sondierung, angewendet.

### Verkettung

Bei der Strategie Verkettung wird bei Kollision an der Stelle des berechneten Index eine einfach verkettete Liste eingefügt, wobei das vorderste Element immer das zuletzt hinzugefügte Element ist.


h(K) = K mod 11
Gegeben: 1234, 4321, 256, 2222, 16, 1111, 954, 7532, 368, 600, 86

| Index | 0  | 1 |   2  |  3  | 4 |  5  |  6  | 7 |   8  |   9   | 10 |
| ----- |----|---|------|-----|---|-----|-----|---|------|-------|----|
|       |1111|   | 1234 | 256 |   | 368 | 600 |   | 7532 |   86  |    |
|       |2222|   |      |     |   |  16 |     |   |  954 |  4321 |    |


### Lineare Sondierung

Bei der linearen Sondierung wird, im Gegensatz zur Verkettung, jedes Element an einer eigenen Stelle in Tabelle abgelegt. Kommt es zu Kollisionen wird die nächste freie Zelle genutzt. Das bedeutet das ein Element nicht immer unter dem Index auffindbar ist der von der Hashfunktion zugewiesen wird.

---
---

## Teilstringsuche nach Boyer-Moore

Beim Boyer-Moore Algorithmus wird das Muster unter dem Text angelegt und es wird immer nur das letzte Zeichen des Musters mit dem darüber liegenden Zeichen des Textes verglichen. Sind die beiden Zeichen nicht gleich so wird im Muster nach dem gleichen Zeichen wie im Text gesucht. Ist ein Zeichen gleich so wird das Muster bis zu dem Textzeichen weiter nach rechts geschoben. Ist es nicht gleich so wird das gesamte Muster bis nach dem verglichenen Textzeichen nach rechts gerückt. Dieses Vorgehen wird so lange wiederholt bis entweder das Muster gefunden wurde oder bis man den gesamten Text durchsucht hat. Ist das letzte Zeichen des Musters gleich dem zu vergleichenden Textzeichen so wird das restliche Muster von rechts nach links mit dem Text verglichen.


![Boyer-Moore](/Boyer-Moore-Tabelle.png "Boyer-Moore Graphisch")

---
---


## Quellen

https://cg.informatik.uni-freiburg.de/course_notes/info2_13_avlBaum.pdf

https://www1.pub.informatik.uni-wuerzburg.de/databases/Binaere_Suchbaeume/avl_trees/rebalancing/rotations.html

Robert Sedgewick / Kevin Wayne | Algorithmen und Datenstrukturen

https://visualgo.net/de/bst

https://moodle.mosbach.dhbw.de/pluginfile.php/421473/mod_resource/content/1/theoretische_informatik_II_2020.pdf

http://ac.informatik.uni-freiburg.de/lak_teaching/ss_06/info2/Slides/12_Hashverfahren_Verkettung_der_Ueberlaeufer.pdf