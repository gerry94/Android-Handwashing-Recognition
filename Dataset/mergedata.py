import pandas as pd 

df1= pd.read_csv("dataset/accelerometer/handwash.csv") 
df2= pd.read_csv("dataset/accelerometer/no_handwash.csv") 

frames = [df1,df2]
result = pd.concat(frames)
result.to_csv( 'dataset/acceldata.csv', index = False)

df1= pd.read_csv("dataset/gyroscope/handwash.csv") 
df2= pd.read_csv("dataset/gyroscope/no_handwash.csv") 

frames = [df1,df2]
result = pd.concat(frames)
result.to_csv( 'dataset/gyrodata.csv', index = False)