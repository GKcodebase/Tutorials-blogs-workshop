# A walk through through "Melbourne house price dataset" and create your first machine learning model using linear regression

import pandas as pd
#loading the dataset
data = pd.read_csv('melb_data.csv')

#peeking to your data using head() function in pandas
print("Peeking at the data : \n",data.head())

#Printing data type of each attributes
print("Data type  of each attributes : ",data.dtypes)

#lets see how much data is present
print("Amount of data present : ",data.shape)

#Lets get into data by descriptive statistcs {describe function in panda}
print("Describing the dataset using statistics \n",data.describe())

#Removing coloumns with null values
cols_with_missing = [col for col in data.columns if data[col].isnull().any()]
data.drop(cols_with_missing, axis=1, inplace=True)

#Removing coloumn with string datatype
data = data.select_dtypes(exclude=['object'])
data.head()

#Setting the output of the model
y = data.Price
data.drop(['Price'],axis=1,inplace = True)

#Let's get a visualiazation of the data using plots
from matplotlib import pyplot as plt
data.hist()
plt.show()

data.plot(kind='density', subplots=True, layout=(3,3), sharex=False)
plt.show()

#Splitting the dataset for training and testing
from sklearn.model_selection import train_test_split
X_train,X_test,Y_train,Y_test=train_test_split(data,y,test_size=0.2,random_state=5)

#Linear Regression model
print("Developing the Linear Regression Model")
from sklearn.linear_model import LinearRegression
model = LinearRegression() # describing the model
model.fit(X_train,Y_train) # Training the model

#Predicting the output for test dataste
predict = model.predict(X_test)

#Evaluating the model
from sklearn.metrics import mean_squared_error
import numpy as np
RMSE = mean_squared_error(Y_test,predict)
print("Mean Square Error for the model : ",np.sqrt(RMSE))

