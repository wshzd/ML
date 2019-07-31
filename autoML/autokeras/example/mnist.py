import os
# path = '/Users/arron/autokeras/'
# os.chdir(path)

from keras.datasets import mnist
# from autokeras.image.image_supervised import ImageClassifier
from autokeras import ImageClassifier

(x_train, y_train), (x_test, y_test) = mnist.load_data()
x_train = x_train.reshape(x_train.shape + (1,))
x_test = x_test.reshape(x_test.shape + (1,))

# 1. 将 verbose 指定为 True 意味着搜索过程将打印在屏幕上供我们查看
clf = ImageClassifier(verbose=True)
# 2. 在 fit 方法中，time_limit 参数是指以秒为单位的搜索时间限制
clf.fit(x_train, y_train, time_limit=12 * 60 * 60)
# 3. final_fit 是模型找到最佳模型架构后进行的最后一次训练。将 retrain 参数指定为 True 意味着将重新初 始化模型的权重
clf.final_fit(x_train, y_train, x_test, y_test, retrain=True)
# 4. 在评估模型在测试集上的效果后，print(y) 将显示模型准确率
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

