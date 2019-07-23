from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn2pmml.feature_extraction.text import Splitter
from sklearn.cluster import KMeans
from sklearn2pmml.pipeline import PMMLPipeline
from sklearn2pmml import sklearn2pmml

# 构建pipeline
pipeline = PMMLPipeline([("td_vector", TfidfVectorizer(max_df=0.7, min_df=0.01, tokenizer=Splitter(), norm=None)), ("km", KMeans(n_clusters=100, random_state=1000))])
# 注意：PMMLPipeline的最后一个必须是评估器；TfidfVectorizer不能使用norm，而且分词器需要使用Splitter()
# 训练模型 sentences为空格分词的句子或者文件
pipeline.fit(sentences)
# 保存pipeline模型
sklearn2pmml(pipeline, "hzd.pmml")
# 预测结果
print(pipeline.predict(sentences))
