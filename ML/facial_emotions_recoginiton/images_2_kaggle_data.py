#!/usr/bin/env python
# coding: utf-8

# In[1]:
from PIL import Image
import numpy as np
import sys
import os
import csv
import cv2
from keras.preprocessing import image


# In[2]:
# create a list of files in a directory
def createFileList(myDir, format='.jpg'):
    fileList = []
    print(myDir)
    for root, dirs, files in os.walk(myDir, topdown=False):
        for name in files:
            if name.endswith(format):
                fullName = os.path.join(root, name)
                fileList.append(fullName)
    return fileList


# In[3]:
# load the original images
myFileList = createFileList('./2B/')


# In[4]:
# load haarcascade classifier, purpose is to detect faces in pictures
face_cascade = cv2.CascadeClassifier('cascades/haarcascade_frontalface_default.xml')

for file in myFileList:
    print(file)
    #img_file = Image.open(file)
	# img_file.show()
    img = cv2.imread(file)

    # get original image parameters...
    #width, height = img_file.size
    #format = img_file.format
    #mode = img_file.mode

    # Make image Greyscale
    #img_grey = img_file.convert('L')
    #img_grey.save('result.png')
    #img_grey.show()
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    
    # Save Greyscale values
    #gray = np.asarray(img_grey.getdata(), dtype=np.int).reshape((img_grey.size[1], img_grey.size[0]))
    #gray = gray.flatten()
    
    faces = face_cascade.detectMultiScale(gray, 1.3, 5)
    for (x,y,w,h) in faces:
        detected_face = img[int(y):int(y+h), int(x):int(x+w)] #crop detected face
        detected_face = cv2.cvtColor(detected_face, cv2.COLOR_BGR2GRAY) #transform to gray scale
        detected_face = cv2.resize(detected_face, (48, 48)) #resize to 48x48
        detected_face = detected_face.flatten()
    
    # Save Greyscale values    
	with open("img_pixels5.csv", 'a') as f:
		writer = csv.writer(f, delimiter=' ')
		writer.writerow(detected_face)




