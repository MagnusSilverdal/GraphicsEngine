# GraphicsEngine

Ett projekt för att visa hur utvecklingsprocessen kan se ut när programmeraren arbetar med något som hen inte ännu behärskar.
Det betyder att det inte är möjligt att planera och förutse den slutliga lösningen på det sätt som förväntas av en programmerare
som "bara" löser ett programmeringsproblem. Arbetet kommer att betå av en sekvens där programmeraren först utfoskar ett område
för att sedan kunna bygga upp nästa del i lösningen. Oundvikligen kommer det att uppstå situationer när ny kunskap visar att tidigare
lösningar inte duger.

# Problemformulering
Skapa ett system i Java för att hantera grafik. Systemet ska kunna rendera grafik i 2 och 3 dimensioner och ska utgå från
native-java (inga externa bibliotek). Målet är att ha verktyg för att effektivt hantera och rita pixlar, linjer, polygoner och bilder i 2d,
samt att hantera och rendera 3d-grafik baserad på noder och ytor (vertex resp face) i t.ex filformaten off eller obj. Systemet bör
hantera wireframe och solid rendering, gärna med någon form av shading (ljussättning) eller texturer.

# TODO
* Implementera algoritmer för att rita linjer
* Skapa klasshierarki för de olika 2d-typerna
* Implemenetera kopiering av flera pixlar (sprites och bilder)
* Skapa ett system av tester och exempel för att visa de olika funktionerna i motorn

# Done
1. 2019-03-19 Implementerat den första 2d-primitiven, en punkt
1. 2019-03-19 Implementerat en animeringslösning för att möjliggöra updatering av tillstånd och skärm (ups och fps)
2. 2019-03-19 Implementerat en enkel mandelbrotgenerator
3. 2019-03-19 Bestämmt färgen på en given pixel
4. 2019-03-19 Utformat ett system som får tillgång till grafikminnet
5. 2019-03-19 Formulerat ett problem att lösa

# Versioner