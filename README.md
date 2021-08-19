# spring-app-XMS-CSV-JSON-file-importer

web application - a tool for uploading Excel, CSV, JSON files from the disk, which later displays the content of the uploaded data records on the GUI
 if another file is uploaded, UPDATE is done and the list of displayed records is updated. I added in every record
 field what file type, e.g. XLS / XLSX, CSV, JSON. If empty it will add empty content. All read records are added
to MySQL database -> database-> readfiledata, table -> policy.

 adds 2 files for excel and csv testing:
 [Kopia Example DATA CSV.csv](https://github.com/PatrykPrusko2019/spring-app-XMS-CSV-JSON-file-importer/files/7017810/Kopia.Example.DATA.CSV.csv)
[Kopia Example DATA XLS.xlsx](https://github.com/PatrykPrusko2019/spring-app-XMS-CSV-JSON-file-importer/files/7017811/Kopia.Example.DATA.XLS.xlsx)


adds a photo of the application's appearance and the content in the MySQL database:
![Przechwytywanie](https://user-images.githubusercontent.com/47269638/130147922-956b5c95-4e0a-4d7e-afac-40404841d5e8.JPG)
![zawartosc w bazie danych](https://user-images.githubusercontent.com/47269638/130147925-ae08c272-a542-4229-bb5a-9af6e4606efb.JPG)


