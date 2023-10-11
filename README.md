# Razvoj mobilnih aplikacija, *Activity Lab*

Repozitorijum sa projektom sa drugog časa praktičnih vežbi iz predmeta Razvoj mobilnih aplikacija.

Cilj ovih vežbi je upoznavanje sa pojmom *Android Activity Lifecycle*-a, kao i bolje razumevanje načina na koji se Android nosi sa rekonfiguracijom u konjukciji sa životnim ciklusom aktivnosti.
# 1 Aplikacija *ActivityLab*
Aplikacija koja će služiti kao osnova današnjih vežbi se zove *ActivityLab* i sastoji se od dve aktivnosti. *Layout* resursi dolaze gotovi sa aplikacijom i od studenata se ne traži da ih modifikuju.
## 1.1 *ActivityOne*
Prva aktivnost se zove *ActivityOne* i prikazana je na slici 1. Ova aktivnost izbacuje poruke na *Logcat* prozor u okviru IDE-a koristeći `Log.i()` metod svaki put kada se pozove bilo koji od *callback* metoda životnog ciklusa aktivnosti: `onCreate()`, `onRestart()`, `onStart()`, `onResume()`, `onPause()`, `onStop()` ili `onDestroy()`. Ova aktivnost takođe treba da nadgleda i prikazuje informacije o povratnom *callback* metodima životnog ciklusa aktivnosti: `onCreate()`, `onRestart()`, `onStart()` i `onResume()`. 

Aktivnost će održavati stanje jednog brojača za svaki od ovih metoda. Ovi brojači broje koliko puta je svaki od ovih metoda bio pozvan od poslednjeg pokretanja aktivnosti *ActivityOne*. Imena metoda i njihov trenutni broj poziva treba uvek da se prikazuju kad god je vidljiv korisnički interfejs *ActivityOne*.

**[❗]** Nemojte deklarisati brojače kao statičke promenljive budući da će cilj sledeće vežbe biti čuvanje stanja između rekonfiguracija.
## 1.2 *ActivityTwo*
Klikom na dugme *Start Activity Two*, *ActivityOne* reaguje aktiviranjem aktivnosti *ActivityTwo* koja je prikazana na slici 2. Prilikom prelaska između dve aktivnosti pozivaju se različite *callback* metode životnog ciklusa aktivnosti pri čemu se odgovarajući brojači inkrementiraju. *ActivityTwo* prikazuje dugme *Close Activity* kojim se zatvara aktivnost, što je moguće postići i korišćenjem *Back* dugmeta Android sistema. 

Relevantne *layout* datoteke i ovog puta su dostupne i ne zahteva se njihova modifikacija. Kao i *ActivityOne* i *ActivityTwo* će pratiti pozive četiri specifične *callback* metode vezane za životni ciklus aktivnosti, i zatim će prikazati odgovarajuća imena metoda i broj poziva. Takođe, na *Logcat* prozor se izbacuje log poruka svaki put kada *ActivityTwo* izvrši bilo koji *callback* metod životnog ciklusa.
# 2 Zadaci
Unutar terminala (MacOS ili Linux) ili *PowerShell*-a navigirati do direktorijuma namenjenog projektima koji će biti predmet vežbi. Klonirati inicijalni projekat korišćenjem sledeće `git` komande:
```console
git clone git@github.com:NikolaVetnic/pmf.rma.activity-lab.git vas-ciljani-dir
```

Parametar `vas-ciljani-dir` je naravno direktorijum u koji želite da klonirate projekat. Otvoriti projekat unutar Android Studio IDE-a.

URL repozitorijuma je: [https://github.com/NikolaVetnic/pmf.rma.activity-lab](https://github.com/NikolaVetnic/pmf.rma.activity-lab)
## 2.1 Prvi zadatak
Prvi zadatak tiče se obe aktivnosti, odnosno relevantni fajlovi su `ActivityOne.java` i `ActivityTwo.java`. Svih pet nabrojanih koraka neophodno je implementirati za obe aktivnosti:

1. Kreirati četiri ne-statičke brojačke promenljive, po jednu za svaki od metoda životnog ciklusa nabrojanih ranije. Inkrementirati ove vrednosti pri pozivu odgovarajućih metoda.

2. Kreirati četiri promenljive tipa `TextView`, od kojih svaka služi za prikazivanje vrednosti jedne od brojačkih promenljivih. `TextView` promenljive bi trebale biti dostupne u svim metodima i trebale bi inicijalno biti instancirane unutar `OnCreate()` metoda. Ove promenljive se oslanjaju na `Id` vrednosti elemenata na koje se odnose, a one se mogu pronaći unutar `res/layout/activity_one.xml` fajla:
```xml
	<TextView android:id="@+id/create"  
	    android:layout_width="wrap_content"  
	    android:layout_height="wrap_content"  
	    android:text="@string/onCreate" />
``` 

3. *Override*-ovati četiri metode životnog ciklusa koje se nadgledaju. U svakom od metoda se treba ažurirati odgovarajući brojač i pozvati `displayCounts()` metod kako bi se ažurirao korisnički interfejs.

4. Implementirati `OnClickListener` za *Start Activity Two* dugme - ovo se tiče samo aktivnosti *ActivityOne*. U narednom listingu dat je *stub* metoda koji treba implementirati:
```java
	launchActivityTwoButton.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			// This function launches ActivityTwo
			// Hint: use Context’s startActivity() method
			...
		}
	}
```

5. Implementirati `OnClickListener` za *Close Activity* dugme - ovo se tiče samo aktivnosti *ActivityTwo*. U narednom listingu dat je *stub* metoda koji treba implementirati
```java
	closeButton.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			// This function closes ActivityTwo
			// Hint: use Context’s finish() method
			...
		}
	}
```
## 2.2 Drugi zadatak
Drugi zadatak tiče se isključivo aktivnosti *ActivityOne*, odnosno sve izmene prave se u okviru fajla `ActivityOne.java`:

1. Implementirati metod čiji je zadatak da sačuva vrednosti brojača. Kada se aktivnost prekine sa mogućnošću kasnijeg ponovnog pokretanja Android poziva metod `onSaveInstanceState()` koji aktivnosti daje priliku da sačuva bilo kakve podatke relevantne za datu instancu u slučaju potrebe za kasnijim ponovnim pokretanjem. Ukoliko Android ne očekuje da se aktivnost *restart*-uje ovaj metod neće biti pozvan, kao što je npr. slučaj kod korišćenja *Close Activity* dugmeta u drugoj aktivnosti (za više informacija konsultovati [dokumentaciju](https://developer.android.com/reference/android/app/Activity.html)):
```java
	// Save per-instance data to a Bundle (a collection of key-value pairs).
	public void onSaveInstanceState(Bundle savedInstanceState) {
		...
	}
```

2. Implementirati postavljanje sačuvanih vrednosti brojača nakon poziva metode životnog ciklusa u okviru `OnCreate()` metoda:
```java
	protected void onCreate (Bundle savedInstanceState)
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		
		// Has previous state been saved?
		if (savedInstanceState != null){
			// Restore value of counters from saved state
		}
	}
```
## 2.3 Očekivano ponašanje
Svaki od metoda životnog ciklusa vezan moguće je okinuti nekom interakcijom sa korisničkim interfejsom aplikacije:
- `OnCreate()` - poziva se kada se aktivnost prvi put kreira i koristi se za početnu inicijalizaciju,
- `OnStart()` - poziva se nakon `OnCreate()` i `OnRestart()`, indikuje da će aktivnost postati vidljiva ali još uvek nije u prvom planu,
- `OnResume()` - poziva se nakon `OnStart()` metoda, indikuje da korisnik može da interahuje sa aplikacijom, 
- `OnRestart()` - poziva se nakon što je aktivnost stopirana, neposredno pre nego što postane ponovo vidljiva.

Prva tri metoda reaguju na promenu orijentacije uređaja, bilo u emulatoru bilo fizičkog uređaja. Poslednji metod će u slučaju prve aktivnosti biti pozvan kada se sa druge aktivnost tapne na *Close Activity* dugme, dok u slučaju druge neće biti pozvan nikada.

Na opisane načine može se ručno testirati responzivnost aplikacije i ispravnost implementacije. Pored manuelnih testova dostupna je i kolekcija od šest automatizovanih testova.