@echo off
for %%F in (.\lib\*.jar) do set CLASS_PATH=%1;%CLASS_PATH% %%F;

java -cp %CLASS_PATH% com.rhythmdiao.Launcher