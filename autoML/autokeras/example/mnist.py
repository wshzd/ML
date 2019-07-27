import os
# path = '/Users/arron/autokeras/'
# os.chdir(path)

from keras.datasets import mnist
# from autokeras.image.image_supervised import ImageClassifier
from autokeras import ImageClassifier

(x_train, y_train), (x_test, y_test) = mnist.load_data()
x_train = x_train.reshape(x_train.shape + (1,))
x_test = x_test.reshape(x_test.shape + (1,))

clf = ImageClassifier(verbose=True)
clf.fit(x_train, y_train, time_limit=12 * 60 * 60)
clf.final_fit(x_train, y_train, x_test, y_test, retrain=True)
y = clf.evaluate(x_test, y_test)
print(y)

# export the model
clf.export_keras_model('my_model.h5')
#
# from keras.models import load_model
# model = load_model('my_model.h5')
#
# from keras.utils import plot_model
# plot_model(model, to_file='my_model.png')

