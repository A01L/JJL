@echo off
chcp 65001
javac Init.java core/*.java
java Init.java %*
