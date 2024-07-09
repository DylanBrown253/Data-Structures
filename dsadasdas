import numpy as np
import tensorflow as tf
import cv2
import os

trainPath = "D:/Dowloads/dataEmotions/archive/train"
testPath = "D:/Dowloads/dataEmotions/archive/test"

FolderList = os.listdir(trainPath)
FolderList.sort()

print(FolderList)

images_train = [] # x_train
labels_train = [] # y_train

images_test = [] # x_test
labels_test = [] # y_test

#Load training data into the arrays



#Training Set
for i , category in enumerate(FolderList):
    files = os.listdir(trainPath + "/" + category)
    for file in files:
        print(category+ "/" + file)
        img = cv2.imread(trainPath + "/" + category + '/{0}'.format(file),0)
        images_train.append(img)
        labels_train.append(i)
 

print("\nNumber of images: ")
print(len( images_train))

img1 = images_train[0]

cv2.imshow("img1",img1)
cv2.waitKey(0)


print("\nNumber of labels")
print(labels_train)
print(len(labels_train))


#Test Set
FolderList = os.listdir(testPath)
FolderList.sort()

for i , category in enumerate(FolderList):
    files = os.listdir(testPath + "/" + category)
    for file in files:
        print(category+ "/" + file)
        img = cv2.imread(testPath + "/" + category + '/{0}'.format(file),0)
        images_test.append(img)
        labels_test.append(i)
  


print(len(images_test))


#convert data to numpy
            
images_train = np.array(images_train, 'float32')
labels_train = np.array(labels_train, 'float32')
images_test = np.array(images_test, 'float32')
labels_test = np.array(labels_test, 'float32')

print(images_train.shape)
print(images_train[0])

#two tasks :
#  Normalize the image : 0 to 1
#  Add another dimention to the data : (28709, 48, 48, 1)

images_train = images_train/255.0
labels_train = labels_train/255.0

#reshape

numOfImages = images_train.shape[0] #28709
images_train = images_train.reshape(numOfImages, 48, 48, 1)


print(images_train[0])
print(images_train.shape)



numOfImages = images_test.shape[0] #28709
images_test = images_test.reshape(numOfImages, 48, 48, 1)

print(images_test)
#print(images_test.shape)
