package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

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
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
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
        throw new UnsupportedOperationException("Ikke kodet ennå!");
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
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
