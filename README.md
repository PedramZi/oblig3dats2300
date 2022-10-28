# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Pedram Ziaei, S362046, s362046@oslomet.no


# Oppgavebeskrivelse

NB! på øvingstimen ventet jeg en og halv time for å fikse ting med gradle og hadde litt problem med build structure men 
fikk ikke mye hjelp i timen men ble bedt om å forklare situasjonen fordi at det har noe med rettingen å gjøre.

I oppgave 1 så gikk jeg frem ved å initialisere roten som r, z ble satt null og komper som en komperator som er
en hjelpevariabel. deretter loopes gjennom nodene eller treet vårt med en while løkke der det sjekkes foreldre, rot og
barn og verdiene blir sammenlignet og da flyttes. videre lager vi ny node for verdien og går gjennom tre if tester som
sikrer noden. om alt er ok så antallet økes og boolean returnerer true.

I oppgave 2 brukte jeg while løkke,compare til å sammenligne verdier om de ikke er i rot
og de blei gjennom en del if tester som klargjør ting om det skjer det og det.
for antall skulle bare returneres antall siden denne funksjonen trengte ikke noe mer.
sammen skjer på tøm og da satt jeg antall lik null og hvis det er slik så er det tøm.

I oppgave 3 samme prinsip while løøker brukes og loppes gjennom tret og det innkommer en del if tester
som sikrer oss tilfeller eller at det nærmere oss ønskende situasjon. ting har blitt kommentert og ser
faktisk bra.

I oppgave 4 brukes første postorden og nest postorden og thats it. det blir tatt rekursivt ut i en andre
funksjonen. fuksjonen tar inn oppgave og hvilken siden dette skal skje og til slutt skrives ut.

I oppgave 5 ble brukt eksemplet vi fikk i obligen for å omgjøre binærtreet til array. ved hjelp av
new SBinTre<>(compaartor.naturalOrder()) laget jeg nytt tre og tok verdier ut av med queue funksjonen.
videre blei de brukt i en array listet og ble limt inn.

I oppgave 6 brukte jeg eksemplet og boken som kilde. kopierte koder fra programkode 5.2 8 d). brukte mangevis
if tester som faktisk øddelegger farten for å avklare tre tilfeller som er mulig. det som var nytt
enn opppgave 5 var at jeg brukte en for løkke som innholder int verdi : a og leggger verdien inn i treet.

