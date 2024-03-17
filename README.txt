1) Creare utilizator

	Pentru a rezolva acest task am facut o clasa in care am implementat o metoda de scriere 
in fisierul users.txt, pentru a verifica daca username-ul pe care vreau sa il scriu in fisier mai
exista deja si pentru a verifica daca parolele coincid cu username-urile introduse. De asemenea ,
am facut o metoda statica boolean numita 'authentication' in care verific parametrii furnizati si
daca parola este corecta pentru fiecare din urmatoarele task-uri. In cazul in care 'authentication' 
returneaza 1, autentificarea a avut succes. In caz contrar, afisez unul din mesajele corespunzatoare.

2) Creare întrebare

	Inainte de toate am creat o clasa Questions in care: Am creat o metoda de scriere in fisier 
a intrebarilor. De asemenea , am creat o metoda prin care returnez ID-ul fiecarei intrebari pentru a 
ma ajuta la task-urile ulterioare si m-am folosit de ea pentru a verifica daca intrebarea deja exista. 
Pentru a verifica existenta textului si cea a raspunsurilor m-am folosit de args si am facut un switch 
pentru lungime si am tratat diferite cazuri. Am verificat numarul intrebarilor single printr-o metoda 
in care vad daca dupa 'answer-i-is-correct' am mai multi parametri 1 cu ajutorul lui split. De asemenea 
fac un for din 2 in 2 pasi pentru a parcurge doar ce ma intereseaza. Flag-urile si descrierea le tratez 
in aceeasi metoda pornind indexarea de la 5 si verific daca argumentul split-uit este '-answer-' pentru 
a verifica descrierea , respectiv '-answer-i-is-correct' pentru a verifica flag-urile. Pentru a verifica 
daca am mai multe raspunsuri folosesc un simplu for in for si verific fiecare raspuns cu urmatoarele de 
dupa el pentru a stabili daca mai apare. In cazul in care am vreuna din variante de mai sus, ies cu return. 
Daca nu, scriu intrebarea in questions.txt.

3) Întoarce identificator întrebare după nume
	In acest task folosesc metoda explicata mai sus in care iau ID-ul fiecarei intrebari in functie de 
numarul liniei pe care se afla in questions.txt. In cazul in care metoda returneaza 0 inseamna ca intrebarea
nu exista.

4)  Întoarce toate întrebările din system
	Basically, pentru acest task tot ceea ce fac este sa parcurg liniile din fisierul cu intrebari si, cu un
id declarat in metoda, sa afisez la consola id-ul intrebarii si enuntul acesteia.

5) Creare chestionar
	Pentru acest task am folosit o noua clasa, bineinteles cu 2 variabile: Nume si Numarul de raspunsuri corecte.
Am folosit o metoda de scriere in fisierul quiz.txt pentru a verifica la fel ca pentru clasele precedente daca deja
se afla in fisier. De asemenea, fiecare clasa o mosteneste pe precedenta pentru a nu scrie bucati de cod duplicate.
Spre exemplu metoda 'Duplicates' o folosesc pentru a verifica ce am semnalat mai sus pentru aproape fiecare clasa in
parte. In cazul in care chestionarul nu exista si totul e in regula, acesta se adauga cu succes in fisierul text.

6) Întoarce identificator chestionar după nume
	Am implementat o functie asemanatoare cu cea de la intrebari in care verific daca quiz-ul exista prin ID.
De asemenea, daca acesta exista, afisez ID-ul intrebarii respective din quiz.

7) Întoarce toate chestionarele din system
	La fel ca la questions, implementez o metoda asemanatoare in care afisez id-ul quiz-urilor si numele lor.

8) Întoarce detaliile unui chestionar în funcție de identificator (fără valoarea de adevăr a răspunsurilor)
	Pentru acest task am facut un fisier separat quizinfo.txt in care am scris informatiile necesare afisarii
fiecarui quiz in parte in care pe prima linie am retinut numele sau, pe urmatoarea intrebarea, tipul acesteia si 
urmatoarele informatii pentru raspunsuri. Dupa aceea, scriu un \n dupa fiecare intrebare ca sa stiu ca am trecut
la o intrebare noua.

9) Încarcă răspunsuri chestionar
	Pentru acest task am reusit sa rezolv calcularea punctajului pentru fiecare test individual, insa , desi a 
mers pentru fiecare in parte, cand rulam toata suita de teste se strica scrierea raspunsurilor corecte in fisier
(facusem un fisier separat in care pe linia 1 aveam raspunsurile corecte pentru linia 1, pentru linia 2 la fel etc)
asa ca am lasat-o balta din cauza timpului. In schimb am facut pentru 0/100 de puncte si am verificat autentificarea.

10) Ștergere chestionar
	Pentru acest task am parcurs fiecare linie din quiz.txt, iar cand am ajuns la id-ul dorit am sters linia
respectiva. De asemenea am verificat existenta id-ului quiz-ului dat de la input

11) Întoarce soluțiile mele
	Am afisat toate solutiile quiz-ului cu punctajele aferente

12) Curăță date din aplicație
	Am facut o clasa separata de Cleanup in care golesc fiecare fisier mereu cand '-cleanup-all' este argumentul 0.

	
