import pandas as pd 
from IPython.display import display
import statistics
import numpy as np
from scipy.stats import skew 


# Read data from file 'filename.csv' 
# (in the same directory that your python process is based)
# Control delimiters, rows, column names with read_csv (see later)
#
directories = ['handwash', 'no_handwash']
col = ['mean_x','max_x','min_x','std_x','var_x','ske_x','zcr_x', 'mean_y','max_y','min_y','std_y','var_y','ske_y','zcr_y', 'mean_z','max_z','min_z','std_z','var_z','ske_z','zcr_z', 'handwash']

wf = 50 


for path in directories:
    data = pd.read_csv(path+"/gyrData.csv") 
    df = pd.DataFrame(columns = col)
    for i in range(0,int(len(data.index)/wf+1)):
        arr_x = data.loc[i*wf:i*wf+(wf-1), 'GYR_X'].tolist()
        arr_y = data.loc[i*wf:i*wf+(wf-1), 'GYR_Y'].tolist()
        arr_z = data.loc[i*wf:i*wf+(wf-1), 'GYR_Z'].tolist()
        if len(arr_x) > 2:
            df.at[i,'mean_x'] = statistics.mean(arr_x)
            df.at[i,'max_x'] = max(arr_x)
            df.at[i,'min_x'] = min(arr_x)
            df.at[i,'std_x'] = statistics.stdev(arr_x)
            df.at[i,'var_x'] = statistics.variance(arr_x)
            df.at[i,'ske_x'] = skew(arr_x)
            dt = [(i-statistics.mean(arr_x))/statistics.stdev(arr_x) for i in arr_x ]
            df.at[i,'zcr_x'] = (np.diff(np.sign(dt)) != 0).sum()

        if len(arr_y) > 2:
            df.at[i,'mean_y'] = statistics.mean(arr_y)
            df.at[i,'max_y'] = max(arr_y)
            df.at[i,'min_y'] = min(arr_y)
            df.at[i,'std_y'] = statistics.stdev(arr_y)
            df.at[i,'var_y'] = statistics.variance(arr_y)
            df.at[i,'ske_y'] = skew(arr_y)
            dt = [(i-statistics.mean(arr_y))/statistics.stdev(arr_y) for i in arr_y ]
            df.at[i,'zcr_y'] = (np.diff(np.sign(dt)) != 0).sum()

        if len(arr_z) > 2:
            df.at[i,'mean_z'] = statistics.mean(arr_z)
            df.at[i,'max_z'] = max(arr_z)
            df.at[i,'min_z'] = min(arr_z)
            df.at[i,'std_z'] = statistics.stdev(arr_z)
            df.at[i,'var_z'] = statistics.variance(arr_z)
            df.at[i,'ske_z'] = skew(arr_z)
            dt = [(i-statistics.mean(arr_z))/statistics.stdev(arr_z) for i in arr_z ]
            df.at[i,'zcr_z'] = (np.diff(np.sign(dt)) != 0).sum()
            if path == 'handwash':
                df.at[i,'handwash']=True
            else:
                df.at[i,'handwash']=False

        df.to_csv('dataset/gyroscope/'+path + '.csv', index = False)
        

    
   
