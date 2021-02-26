#####################################
# Color compression usin Kmean      #
# Created By G.K                    #
#####################################

### import libraries
from skimage import data, io
from matplotlib import pyplot as plt
### use this library if you want to input custom image
import imageio
#china  = imageio.imread('custom_image.jpg')
from sklearn.datasets import load_sample_image
### loading image sample from avaliable dataset
Image = load_sample_image('flower.jpg')

### used to get the pixel value of image
print(Image.shape)

### Original picture
#io.imshow(Image)
#plt.show()

### Normaliazing the original image
data = Image/255.0
###  converting  3D matrix into 2D
data = data.reshape(640*427,3)


import warnings
warnings.simplefilter('ignore')
### import clustering from scikit
from  sklearn.cluster import  MiniBatchKMeans
### can be compressed further by incrasing the number of the cluster
kmean = MiniBatchKMeans(8)
### fitting the data into Kmean
kmean.fit(data)
#### clustering the datapoints
new_colors = kmean.cluster_centers_[kmean.predict(data)]
#### converting back to  3D matrix
Image_recoloyred = new_colors.reshape(Image.shape)

### plotting the output
fig,ax = plt.subplots(1,2,figsize=(16,6),subplot_kw = dict(xticks=[],yticks=[]))
fig.subplots_adjust(wspace = 0.5)
ax[0].imshow(Image)
ax[0].set_title('original_image',size = 16)
ax[1].imshow(Image_recoloyred)
ax[1].set_title('8_color image',size =16)
io.imshow(Image_recoloyred)
plt.show()