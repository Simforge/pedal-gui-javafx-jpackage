# pedal-gui-javafx-jpackage
client gui to the arduino code found here https://github.com/vospascal/pedal-arduino/

## help
If you want to develop the software furter please create a pull request or create an issue

## if you like it
if you like it you can donate further developent
https://www.paypal.com/donate?business=TBPE6XCB2XBMW&item_name=pedalbox&currency_code=EUR

----------------------------

### idea
ctrl + ctrl = run commando

----------------------------
### Powershell tips
make sure the $env:JAVA_HOME is set properly inside env.ps1

----------------------------
###Powershell restrictions 
run `Get-ExecutionPolicy` should be returning `Restricted`

open powershell with admin rights
run `Set-ExecutionPolicy unrestricted`

you will see this
`Execution Policy Change
The execution policy helps protect you from scripts that you do not trust. Changing the execution policy might expose
you to the security risks described in the about_Execution_Policies help topic at
https:/go.microsoft.com/fwlink/?LinkID=135170. Do you want to change the execution policy?
[Y] Yes  [A] Yes to All  [N] No  [L] No to All  [S] Suspend  [?] Help (default is "N"):`

Press `A`

if you run `Get-ExecutionPolicy` should be returning `Unrestricted`

now it should work!!!
