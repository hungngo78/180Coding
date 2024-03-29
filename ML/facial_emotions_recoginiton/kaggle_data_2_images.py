#!/usr/bin/env python
# coding: utf-8

__author__ = 'Hung Ngo'

import numpy as np
import cv2
import mxnet as mx
import pandas as pd
import random
import os


# In[1]:
print("Path at terminal when executing this file")
print(os.getcwd() + "\n")

curdir = os.getcwd()


# In[2]:
def gen_record(csvfile,channel):
    data = pd.read_csv(csvfile,delimiter=',',dtype='a')
    labels = np.array(data['emotion'],np.float)
    # print(labels,'\n',data['emotion'])
        
	# convert data['pixels'] into 1 dimension vectors 
    imagebuffer = np.array(data['pixels'])
    images = np.array([np.fromstring(image,np.uint8,sep=' ') for image in imagebuffer])
    del imagebuffer
	
	# convert into 3 dimension matrix
    #num_shape = int(np.sqrt(images.shape[-1]))
    num_shape = 48  # picture resolution : 48x48
    images.shape = (images.shape[0],num_shape,num_shape)
	
    # img=images[0];cv2.imshow('test',img);cv2.waitKey(0);cv2.destroyAllWindow();exit()
    dirs = set(data['Usage'])
    subdirs = set(labels)
    class_dir = {}
    for dr in dirs:
        dest = os.path.join(curdir,dr)
        class_dir[dr] = dest
        if not os.path.exists(dest):
            os.mkdir(dest)
            
    data = zip(labels,images,data['Usage'])
    
	# write to images
    for d in data:
        destdir = os.path.join(class_dir[d[-1]],str(int(d[0])))
        if not os.path.exists(destdir):
            os.mkdir(destdir)
        img = d[1]
        filepath = unique_name(destdir,d[-1])
        print('[^_^] Write image to %s' % filepath)
        if not filepath:
            continue
        sig = cv2.imwrite(filepath,img)
        if not sig:
            print('Error')
            exit(-1)


# In[3]:
def unique_name(pardir,prefix,suffix='jpg'):
    filename = '{0}_{1}.{2}'.format(prefix,random.randint(1,10**8),suffix)
    filepath = os.path.join(pardir,filename)
    if not os.path.exists(filepath):
        return filepath
    unique_name(pardir,prefix,suffix)


# In[4]:
if __name__ == '__main__':
    filename = './fer2013_edited-Sad.csv'
    filename = os.path.join(curdir,filename)
    gen_record(filename,1)








