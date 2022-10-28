package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {
        Node<T> r = rot, z = null;
        int komper = 0;

        while (r != null){//while løkke som sjekker roten er ikke lik null
            z = r; //z blir foreldre til rot
            komper = comp.compare(verdi, r.verdi);//disse to verdiene blir komperer
            r = komper < 0 ? r.venstre : r.høyre;//r flyttes
        }

        r =  new Node<>(verdi, z);//oppretter ny node for verdien

        if (z == null){
            rot = r;   //r blir rot når z er null
        }else if(komper < 0){
            z.venstre = r; //venstrebarn til z
        } else {
            z.høyre = r; //høyrebarn til z
        }

        antall ++;     //antall økes
        return true;
    }

    public boolean fjern(T verdi) {
        if(verdi == null){
            return false;
        }

        Node<T> p = rot, q = null; // q skal være forelder til p
        while (p != null){
            int komp = comp.compare(verdi, p.verdi); //sammenligner
            if (komp < 0){//hvis så går vi venstre
                q = p;
                p = p.venstre;
            }else if(komp > 0){//hvis så, går vi til høyre
                q = p;
                p = p.høyre;
            }
            else break; //finner ikke verdie så settes på break
        }
        if (p == null) return false; //om den verdien var null
        if (p.venstre == null || p.høyre == null) {
            Node<T> barn = p.venstre != null ? p.venstre : p.høyre;
            if (p == rot) {
                rot = barn;
            } else if (p == q.venstre) {
                q.venstre = barn;
            } else q.høyre = barn;

            if (barn != null) {
                barn.forelder = q;
            }
        }else{
                Node<T> s = p, r =p.høyre; //finner neste
                while (r.venstre != null){
                    s = r;// s er forelder til r
                    r = r.venstre;
                }

                p.verdi = r.verdi;
                if(s != p){
                    s.venstre = r.høyre;
                } else {
                    s.høyre = r.høyre;
                }
                if (r. høyre != null){
                    r.høyre.forelder = s;
                }
            }
        antall--;
        return true;
    }

    public int fjernAlle(T verdi) {
        if (tom()){
            return 0;
        }
        int fjernetAntall = 0;
        while (fjern(verdi)){
            fjernetAntall++;
        }
        return fjernetAntall;
    }

    public int antall(T verdi) {
        Node<T> r = rot;
        int duplikater = 0;

        while( r != null){
            int komper = comp.compare(verdi, r.verdi); //sammenlignes den ønskende verdi inn med roten
            if(komper < 0){   //
                r = r.venstre;
            } else if (komper == 0){
                duplikater ++; //antallet av ønsknede tallet økes
                r = r.høyre;
            }
        }
        return duplikater;
    }

    public void nullstill() {
        if (antall == 0){//returneres ingenenting om antallet er null
            return;
        }

        Node<T> p = rot;

        int stops = antall;
        p = førstePostorden(p);

        while (stops != 0){ // der hvor antallet ikke er null så skjer iterasjon som fjerner
            if(p != null){
                fjern(p.verdi);
            }
            if (p != null){
                p.verdi = null;
            }
            if (p != null){
                p = nestePostorden(p);//bruker denne funksjonen for å gå videre til neste
            }
            stops--;
        }
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        Objects.requireNonNull(p); //sjekkes mot nullverdi

        while(true){
            if(p.venstre != null){//om venstrebarn til p ikke er lik null
                p = p.venstre; //setter p lik venstre barn
            } else if (p.høyre != null){//om høyrebarn ikke heller er lik null så
                p = p.høyre; //setter p lik høyrebarn
            } else {
                return p;
            }
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        Node<T> f = p.forelder; //f er forelder

        if (f == null){
            return null; //om foreldre er null da er så kan ikke noe mer finnes
        }

        if (f.høyre == p || f.høyre == null){
            return f;//foreldre returners dersom høyrebarn av foreldre er  p eller om det er null
        } else {
            return førstePostorden(f.høyre);//ellers resten ska sjke som førstepostorden
        }
    }

    public void postorden(Oppgave<? super T> oppgave) {
        Node<T> p = rot;

        Node<T> fors = førstePostorden(p);//finner første noden
        oppgave.utførOppgave(fors.verdi);
        while (fors.forelder != null){
            fors = nestePostorden(fors); //forste/fors blir oppdatert
            oppgave.utførOppgave(Objects.requireNonNull(fors).verdi);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if(p == null){
            return;
        }

        postordenRecursive(p.venstre, oppgave);//tar kun venstrebarn av p
        postordenRecursive(p.høyre, oppgave);// tar kun høyrebarn av p

        oppgave.utførOppgave(p.verdi);//utfører
    }

    public ArrayList<T> serialize() {
        ArrayList<T> nivatre = new ArrayList<>();//Initialiserer Arrayet
        Queue<Node<T>> queue = new LinkedList<>();//kø

        queue.add(rot);//legger roten som første element
        while (!queue.isEmpty()){//while treet ikke er tøm så
            Node<T> p = queue.remove();//fjernen verdien i queue fra treet
            nivatre.add(p.verdi); // legger inn øverste verdien i arrayet

            if (p.venstre != null){
                queue.add(p.venstre); // venstrebarn legges i køen
            }
            if (p.høyre != null){
                queue.add(p.høyre); // høyrebarn legges i køen
            }
        }
        return nivatre;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        SBinTre<K> tre = new SBinTre<>(c);

        for (K verdi : data){
            tre.leggInn(verdi);
        }
        return tre;
    }


} // ObligSBinTre
