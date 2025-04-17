:: This is an example script used for local development and testing of your resource pack.
:: It expects to be ran inside a directory with your resourcepack in a ./pack subdirectory.


@echo off

mkdir ".\work" 2>nul
cd ".\work"

del "local-pack.zip" 2>nul

echo Processing WSEE models...

xcopy "../wsee_entity_bbmodels" "./wsee_entity_bbmodels" /E /I /Y /Q 2>nul
java -jar ../WSEEPackUtil-1.0.jar "..\pack"

echo Zipping...
"C:\Program Files\7-Zip\7z.exe" a -tzip ".\local-pack.zip" ".\wsee_injected_resourcepack\*"


echo Installing To Client...
cd "../"
copy ".\work\local-pack.zip" "%UserProfile%\AppData\Roaming\.minecraft\resourcepacks\local-pack.zip"

echo Done!  Please use F3+T to reload in game.  Remember, server pack takes priority over changes.
echo TODO move wsee files to test server directory

exit