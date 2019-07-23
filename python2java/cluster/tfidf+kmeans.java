package com.xxx;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PMML_TopicExtraction {
    private Evaluator loadPmml(){
        PMML pmml = new PMML();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("C:\\hzd.pmml");         #获取pmml文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(inputStream == null){
            return null;
        }
        InputStream is = inputStream;
        try {
            pmml = org.jpmml.model.PMMLUtil.unmarshal(is);
        } catch (SAXException e1) {
            e1.printStackTrace();
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }finally {
            //关闭输入流
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
        Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
        pmml = null;
        return evaluator;
    }
    private Integer predict(Evaluator evaluator,String input_sentence) {
        Map<String, String> data = new HashMap<String, String>();
        data.put("x1", input_sentence);

        List<InputField> inputFields = evaluator.getInputFields();
        //过模型的原始特征，从画像中获取数据，作为模型输入
        Map<FieldName, FieldValue> arguments = new LinkedHashMap<FieldName, FieldValue>();
        for (InputField inputField : inputFields) {
            FieldName inputFieldName = inputField.getName();
            System.out.println(inputFieldName);
            Object rawValue = data.get(inputFieldName.getValue());
            FieldValue inputFieldValue = inputField.prepare(rawValue);
            arguments.put(inputFieldName, inputFieldValue);
        }
        System.out.println(arguments);

        Map<FieldName, ?> results = evaluator.evaluate(arguments);
        List<OutputField> output = evaluator.getOutputFields();
        // 获取聚类的结果，这个与分类算法evaluator.getTargetFields()不同
        Integer cluster_label = Integer.valueOf(results.get(null).toString().substring(8,10));
        System.out.println(cluster_label);
        System.out.println(results.get(null));
        return cluster_label;
    }
    public static void main(String args[]){
        PMML_TopicExtraction demo = new PMML_TopicExtraction();
        Evaluator model = demo.loadPmml();
        demo.predict(model,"姑娘 们 请 远离 吃软饭 男人 姑娘 们 请 远离 吃软饭 男人 01 大厅 里 乌 泱泱 地 挤满 来 参加 婚礼 人 新娘 小茹 笑靥 如花 一 张嘴 乐得 始终 没有 合拢 过本 就 不大 眼睛 挤得 更是 眯成 一条 缝 新郎 李伟 露出 胜利者 微笑 有种 春风得意 须尽欢 得意 笑容可掬 地 和 每 一位 亲朋好友 打着 招呼 一米 八 帅气 新郎 和 一米 五 矮胖 新娘 显得 有些 不 般配 但 新娘 需要 一个 爱 她 又 排场 男人 来 照顾 她 新郎 需要 一个 能助 自己 一臂之力 女人 来 辅佐 自己 两个 人 各自 打着 自己 小九九 走到 一 起来 参加 婚礼 宾客 出乎意料 多 看似 来 道贺 祝福 实则 来看 热闹 这场 充满 利用 与 陷阱 婚姻 能 维持 多久 小茹 因为 村子 里 拆迁 得到 一笔 上百万 拆迁 款有 家室 李伟用 三寸不烂之舌 终于 打动 小茹 两人 在 你 虚情 假义 我 真情实意 中 慢慢 产生 情愫 小茹 被 帅气 李伟迷 得 神魂颠倒 不能自拔 李伟 也 豪情壮志 地 抛妻 弃子 来到 小茹 身边 照料 她 全心全意 爱 她 钱 总能 使 无数 不 相爱 人 打着 爱 名义 走 到 一起 李伟 有名 花花公子 虽然 没钱 但 凭借 自己 一张 能言善辩 嘴骗 得 无数 姑娘 芳心 个 既 骗财 又 骗色 伪君子 小茹 长相 一般 身材 矮胖 人 又 老实 没 工作 性格 单纯 没有 任何 心机 小茹 就是 这样 没有 经受 得 糖衣炮弹 攻击 很快 醉倒在 李伟 温柔乡 里且 不顾一切 反对 要 和 李伟 同生死 共患难 女人 天生 耳根子 软 总能 被 情话 俘虏 对于 一个 不 经 世事 女人 更是 手到擒来 男人 说 几句 甜言蜜语 做 两顿 早餐 送个 花 就 被 感动 得 痛哭流涕 以为 自己 遇到 真 爱 对于 某些 渣 男 而言 这 只不过 他 撒下 诱饵 而已 女人 虽然 不 需要 你 阅人 无数 但 需要 有 清醒 头脑 理智 行为 才能 慧眼识 英雄 02 知心朋友 没少 劝说 小茹 恋爱 可以 保管 好 自己 钱小茹 自视 聪明 之人 谁 岂能 轻易 骗取 她 钱 结婚 时 在 李伟 怂恿 之下 买 了 车 买 了 房 他 以 飞跃 式 速度 过 上 小康生活 所有人 都 见证 李伟 从无到有 过程 这 一步步 都 靠 小茹 实现 曾经 那个 落魄 潦倒 男人 不见 站 在 人们 眼前 一个 傲慢 风度翩翩 男人 君子爱财 取之有道 李伟 这财 取之于 一个 手 无 缚鸡之力 女人 之手 让 人 多少 觉得 有些 太失 男人 颜面 今天 小茹 手上 那 笔 巨款 已 所剩无几 全都 变成 实物性 房子 车子 以供 俩 人 享受 她 没本事 没 赚钱 能力 事事 却 要 向 李伟 伸手 此时 桀骜不驯 李伟会 轻易 给 她 钱 花 吗 他 高兴 给 点 不 高兴 骂 两句 娶 个 讨债鬼 她 活生生 把 自己 由 一个 大款 在握 富婆 变成 低声下气 狼狈不堪 女人 他 继续 逍遥 快活 周旋 在 众多 女人 之间 她 在家 像 个 老妈子 小心翼翼 地 伺候 他 生怕 惹 他 不 开心 女人 最 悲哀 完全 相信 男人 那 张嘴 把 未来 所有 幸福 都 赌到 一个 用 嘴 承诺 男人 身上 善良 女人 遇上 渣 男 就是 一场 劫难 劫 你 遍体鳞伤 身无分文 再 善良 女人 都 得 有 保护 自己 能力 能 辨别 非 真假 不要 傻到 沦为 别人 工具 03 女人 天生 爱钱 靠 男人 满足 自己 虚荣心 没 曾 想 这 天下 也 有 靠 女人 上位 男人 这种 吃软饭 男人 更让人 瞧不起 初识 一个 人 始于 颜值 陷入 才华 忠于 人品 好颜值 或许 能 给 你 社交 带来 便利 能 当时 取悦 人 但 没有 一个 好 人品 道德败坏 只会 一个 道貌岸然 渣 男 遭人 唾弃 有 因 就 有果 善恶 自有 报应 丢 出去 荆棘 最终 被 刺痛 还是 自己 你 丢 出去 鲜花 最后 得到 会 满袖 芬芳 你 现在 对 他人 所 做 就是 对 自己 做 结出 或善 或 恶 果实 最终 接收 也 不是 他人 而是 自己 女人 女人 有钱 就是 你 任性 资本 有钱 就是 王道 有钱 你 就 会 拥有 无限 人格魅力 获得 人们 尊重 在 你 拥有 经济 资本 同时 还要 做 一个 智慧 女人 保管 好 自己 财务\n");

    }
}



