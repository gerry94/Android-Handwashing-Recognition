# Android Handwashing Recognition

### Overview ###
This Android Java app is aimed at recognizing if a user washes his hands on the basis of sensor data collected by the Accelerometer and Gyroscope. The data is sampled, fetaures are extracted and fed to a decision-tree classifier built with Weka. If the classification is positive the screen is turned green, else red. 
More details about the functioning are provided in the documentation.

### How to use ###
Install the application on your smartphone and then tie the smartwatch in some way to your non-dominant wrist. Then try washing your hands for at least 7-10 seconds (more details about this in the Documentation).
We didn't own a smarwatch so we used a Samsung Galaxy S5 tied to the user's wrist. The variation in accuracy of the classifier if using a real smartwatch/wearable device will be minimal.

#### Credits ####
This app was developed as a submodule of a larger group project for the "Mobile and Social Sensing Systems" course (MsC in Computer Engineering @ University of Pisa). This project consisted in an application that makes use of Open-Source APIs and software to fight/prevent the spread of the Covid-19 pandemic in similar fashion to the "Immuni" app commissioned by the Italian Government in 2020.
This module was developed by R. Polini, G. Alvaro, E. Scarselli and F. Fornaini.
