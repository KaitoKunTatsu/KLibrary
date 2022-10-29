package KLibrary.wrapper.AbiturKlassenUtils;

import KLibrary.datastructures.foreign.List;

/**
 * !!! Warnung: Verwende nicht die Abiturklasse "List" der Ladesregierung NRW ohne einen solchen Wrapper !!! <br>
 *
 * Diese Klasse bietet die Möglichkeit, die Abiturklasse List mit weniger Schmerzen zu benutzen.
 * Es gibt keine unerwarteten Funktionen; es werden nur solche ergänzt, die jede Liste haben sollte, aber von der Abiturklasse nicht bereitgestellt werden.
 *
 * Außerdem ist diese Klasse teil der KLibrary (https://github.com/KaitoKunTatsu/KLibrary)
 *
 * @version	v1.0.0 | last edit: 19.08.2022
 * @author Joshua H. | KaitoKunTatsu#3656
 */
public class AbiListWrapper<ContentType> {

    private List<ContentType> list;
    private int length;

    public AbiListWrapper() {
        list = new List<>();
        length = 0;
    }

    public AbiListWrapper(List<ContentType> pList)
    {
        list = pList;
        length = getLength(list);
    }

    /**
     *  Das Element an der Stelle pIndex wird um eins nach vorne verschoben und pElement nimmt seinen vorherigen Platz ein.
     **/
    public void insert(ContentType pElement, int pIndex)
    {
        moveCursorToIndex(pIndex);
        list.insert(pElement);
        length++;
    }

    public void insert(ContentType pElement) {list.insert(pElement); length++;}


    public void moveCursorToIndex(int pIndex) {
        int currentIndex = 0;
        list.toFirst();
        while(list.hasAccess() && currentIndex < pIndex) {
            list.next();
            currentIndex++;
        }
    }

    public ContentType getElement(int pIndex) {
        list.toFirst();
        int currentIndex = 0;
        while(list.hasAccess() && currentIndex < pIndex)
        {
            currentIndex++;
            list.next();
        }
        return list.getContent();
    }

    public ContentType getCurrentElement() {return list.getContent();}

    public void printList()
    {
        list.toFirst();
        while(list.hasAccess()) {
            System.out.println(list.getContent().toString());
            list.next();
        }
    }

    /*
    *   @return nur die Größe der Liste... wieso muss man das extra implementieren?
    * */
    public int getLength() {return length;}

    public int getLength(List<ContentType> pList)
    {
        int lLength = 0;
        pList.toFirst();
        while(pList.hasAccess()) {
            lLength++;
            pList.next();
        }
        return lLength;
    }

    /**
     * Gibt die hinterlegte Abiturklasse List zurück<br>
     * Nutzung auf eigene Gefahr: Methoden auf diesem Objekt auszuführen kann zu Schmerzen und fehlerhaftem Verhalten dieses Wrappers führen
     *  */
    public List<ContentType> getList() {return list;}

    public void setList(List<ContentType> pList)
    {
        list = pList;
        length = getLength(pList);
    }

    public void moveCursorToLastElement() {list.toLast();}

    public void nextElement() { list.next(); }

    public boolean isEmpty() {return list.isEmpty();}

    public boolean hasCurrentElement() {return list.hasAccess();}

    public void concat(List<ContentType> pList) {list.concat(pList);}

    public void append(ContentType pElement) {list.append(pElement); length++;}

    public void setCurrentElement(ContentType pElement) { list.setContent(pElement); }

}
