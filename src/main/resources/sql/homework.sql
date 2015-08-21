/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : homework

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-08-21 17:53:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for homework_aticle
-- ----------------------------
DROP TABLE IF EXISTS `homework_aticle`;
CREATE TABLE `homework_aticle` (
  `aticle_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `aticle_category` varchar(255) DEFAULT NULL,
  `aticle_lontent` longtext CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `aticle_coverImg` varchar(255) DEFAULT NULL,
  `aticle_createtime` varchar(255) DEFAULT NULL,
  `aticle_author` varchar(255) DEFAULT NULL,
  `aticle_digest` varchar(255) DEFAULT NULL,
  `aticle_name` varchar(255) DEFAULT NULL,
  `aticle_read_num` varchar(255) DEFAULT NULL,
  `key_words` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`aticle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework_aticle
-- ----------------------------
INSERT INTO `homework_aticle` VALUES ('36a16bdf-6915-42af-ac7f-b93e473f6b0a', '网络转载', '<p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">我在之前的简书文章里提到过，国内的UED圈子现状非常浮躁，门槛不高，混日子的忽悠也多，但与此相对的，UED人又很追求自己的“价值”被他人欣赏、看重，而不是被当成只会画画icon的死美工，不想在PM和研发面前处于弱话语权地位。而在产品层面，现在的APP网站个个号称重视用户体验、重视各种设计细节，但不少看起来非常“设计导向”、配色布局精美、遵循平台规范、偶尔有几个惊艳的微交互创意的精美产品最终却不冷不热甚至不得善终（国外的Twitter#Music，Facebook的一系列App，Path等；国内的Jing.fm，轻单等），被一些设计师眼里的“LOW货”花式吊打，最后灰溜溜退出市场，引发大家对设计驱动价值的怀疑。</p><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">这种现实一度让我非常迷惘，觉得设计对解决现实问题是无力的，想不清楚设计与设计师的核心价值在哪里，不可替代性在哪里，在今年2、3月的时候甚至一度打算放弃做一名设计师，开发起了Android App，试图转岗研发等看起来更“核心”的岗位。但这几个月来，我一直思考的关于设计与设计师的“价值”问题却似乎开始慢慢找到了一些答案，这要感谢我的东家阿里在这短短几十天内带给我的大量启迪，让我找回了在设计道路上坚持下去的热情。写下此文，总结一下这段时间内我对这个问题的思考吧。</p><h2 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 17.5px; white-space: normal; widows: auto;\">目标篇</h2><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">一.你究竟为谁而设计</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">UED从业者都知道User-centered Design的概念，但真正做到的却并不多，更多的现实情况是这样的：为老板而设计——上面的人一拍脑袋就围绕对方的idea屁颠屁颠去考虑详细方案执行，不去深度考虑这样做的本质原因与合理性，缺乏独立思考能力与向上说服抗争的勇气；为Deadline而设计——追求项目按时上线比什么都重，在时间有限的情况下没做好优先级管理，集中到关键设计点上去，结果什么都做了，什么都没做好；为规范而设计——死守iOS/Android平台规范，死守可用性原则，不去思考规范这样做的本质原因（如设备特性，避免和通用交互操作冲突等），把规范凌驾于设计目标和业务目标之上；为自己而设计——以设计师的专业挑剔目光去要求产品，却不考虑到这样的设计让自己满足了，却可能令用户迷惑，没有带给用户想要的东西，反而增加了他们操作理解的成本……背离了设计目标的本质，变成了为设计而设计，创造出来的价值自然也大打折扣。</p><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">二.设计师离用户有多远</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">好了，我们知道要User-centered，并在实际工作中要注意不偏离它，但我们是否真的了解用户的感受？如果是C端的产品，或许我们还可以把自己变成一个重度用户，从自己的角度看问题也变成了从用户的角度看问题，但是B端呢？我们设计的产品的目标用户群，可能和我们本身没有任何重合的地方，这就要求用户研究力量的介入，要求对业务本身有深入的学习和理解，甚至设计师自己亲临其境去角色扮演真实的用户，而不是凭自己的想法去YY。而即使是C端的产品，即使我们自身和产品现在的用户群高度重合，是否可以想得更远一些？比如我们想要提升我们产品的UU，在现有用户群的潜力已经不大的情况下，是否可以考虑向更多的用户开放？而我们对这些新的用户又有多少了解呢？都说要重视用户的反馈，但那些积极反馈的用户、大骂产品某个地方体验不好的用户，是否能代表全部用户和未来潜在用户？比如有些用户会因为产品没有遵循Android Design&amp;Material Design而拒绝使用，但我们完全按他们的想法做了之后，是不是会影响到更大一部分用户群的学习与认知，反而造成产品整体可用性的下滑？</p><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">三.设计的最终目标是什么</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">就像知乎上那句有名的回答一样，“小孩子才看用户体验，成年人只讲利益。”在过去很长一段时间内，我认为设计的目标就是提升产品的用户体验，也对BAT等大厂那些“体验不好”的产品嗤之以鼻。但实际上，提升用户体验只是过程与手段，是完成产品商业目标的一个重要而非全部途径而已。设计最难的地方，不是把用户体验打磨得多么多么极致，而是在多方限制条件下取得平衡，从商业、技术、用户等多方面综合考虑，得出一个“综合最优”而非“设计最优”的解答。在思考设计的时候，也要思考设计背后的产品目标，产品背后的业务目标，对业务能创造什么价值，对公司其他产品能创造什么价值，对公司生态链能创造什么价值，甚至对这个社会、这个国家能创造什么价值等。直击事物的本质，而非沉醉于表面的雕花功夫。</p><h2 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 17.5px; white-space: normal; widows: auto;\">过程篇</h2><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">一.设计工具不是形式主义</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">在学校的时候，我沉迷于研究和模仿各种用户体验与服务设计工具，人物模型、故事板、卡片分类、流程图、站点地图等，不过在前一份侧重视觉而非交互的实习里，并没有太多应用的机会，也迷惘过这些工具的意义是否不大。来淘宝做交互设计师之后，终于可以开始在工作中大量应用这些工具，对照参考内网大量其他设计师的文档，也能模仿得有模有样。</p><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">但很快就被指出了问题，我的分析工具和设计产出发生了脱节，用这些工具得出的结论与设计目标，在实际的交互方案中没有得到太多的展现。但不可否认的是，这些交互设计师的常用工具对梳理整个产品架构逻辑与推导设计目标非常有用，如果根据推导出来的架构和目标去做设计，全程思路比草草画了点Wireframe就急匆匆上Hi-Fi要清楚得多，也减少了后期因考虑不周而全面推倒返工的情况。不过如果你只是形式主义地去模仿它的使用，那对最后的设计产出就没什么实际意义，它就沦为缺少价值的鸡肋了。</p><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">二.展现思考过程而非最终结果</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">阿里的设计师（主要指交互设计师）让我非常钦佩的一点是，他们会在设计文档里完整展现自己的设计思考过程，从背景、项目目标、用户目标、用户分析、设计目标到信息架构、页面框架、多种方案的页面线框图到高保真原型等，通过文档可以清楚地了解他们是怎么一步步得到现在的设计结果的，知道这样的设计是有理有据的，考虑了很多不同的方面，而如果简单粗暴地扔一大张带流程的线框图或高保真原型图草草了事，就难以看到这么设计背后的价值在哪里，相比其他设计方案的优势在哪里，实际说服力也不强。</p><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">三.数据的力量</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">曾经在知乎上看到一个设计师说，最有成就感的事情就是看到自己的设计上线后，产品的XX率蹭蹭蹭涨了多少。设计是一个比较难量化的东西，但数据可以在一定程度上体现和证明设计的价值。个人虽然来阿里的时间不长，但感觉这里非常重视数据，很多设计一开始就有清晰的数据导向。不过我对数据的学习才刚刚开始，可以谈的地方也不多，此文先略过。</p><h2 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 17.5px; white-space: normal; widows: auto;\">合作篇</h2><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">一.别人真的做了我们的活吗</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">现在很多交互设计师抱怨PM抢了自己的活，UI设计师抱怨交互抢了自己的活。嗯，看上去一部分PM也会把Wireframe画得有模有样，看上去一部分交互设计师出的原型图除了颜色和高保真已经没什么区别了，但这就真的意味着别人把我们的活给干了吗？</p><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">虽然现状是PM和交互设计师存在较大的职能重叠，但我觉得二者还是有各自的侧重和优势的。PM更多从全局的观念来看待问题，对业务逻辑有更深入清晰的了解，还常常兼任项目管理，PM做出来的Wireframe，通常只是业务功能模块的堆积；交互更多从设计、从细节体验的角度来看待问题，比如接到需求后常常需要进一步沟通清楚每个业务功能模块的优先级，思考怎么用设计来体现这种优先级关系；比如对产品的架构和流程重新梳理调整，思考怎么让用户以更短更快的路径找到自己的目标。对于繁杂一些的项目，PM需要考虑的东西非常非常多，不太可能在界面细节上花太多精力，这就给了交互设计师充分思考和发挥的空间。</p><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">但如果你懒得主动思考，懒得去质疑，PM给什么就照着画什么，只是纯粹从界面上去“美化”，不去思考模块的目标和优先级，不去思考信息架构的合理性，不去思考产品目标和用户目标，那也怨不得PM把自己的活给抢了吧。</p><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">二.己所不欲，勿施于人，同理心不只是对待用户</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">现在有些设计师非常看重设计的价值，听不得别人把自己叫“美工”，听不得别人说你画个图标美化一下就好了。但有时反过来一想，我们要求别的岗位尊重自己，可自己是否又足够尊重别的岗位呢？我们会不会觉得PM只要动动嘴皮子就好了什么人都能做，我们会不会觉得控件移一个像素在研发那里是分分钟就能改好（实际上可能牵动到整个布局的变化）的事，尊重别人的专业度，其实也是尊重自己。我主张交互设计师在做好本职工作之余，也要懂一点视觉、懂一点前端、懂一点产品，这样才能更好地去和其他职位换位思考，让自己做出更靠谱的设计，赢得团队的信任。</p><h2 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 17.5px; white-space: normal; widows: auto;\">态度篇</h2><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">一.你爱自己的产品吗</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">我最反感的一件事情，就是设计师对自己的产品都没有爱，对自己的产品都不使用。比如用着iPhone设计Android平台的产品，比如用着竞品不用自己的产品（目标是从竞品中得到启发不算），你自己设计的产品自己都不去用，为什么要别人来尊重你的设计、你的产品的价值？</p><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">二.职业不是跳板</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">现在有些人做设计不是因为爱设计，而是把它当做跳板，比如未来好转去做产品，或者觉得做设计门槛低不用懂技术那就做设计了。可你对自己的职位都不尊重，那别人为什么要来尊重你呢？</p><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">三.平台无贵贱之分</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">首先检讨一下，在来淘宝之前，我对产品平台一直抱着很偏执的态度，只喜欢C端移动端，看不起B端PC端，做不了自己喜欢的产品就不爽。但现在我觉得，无论怎样的平台、怎样的产品、怎样的目标用户，都有它的价值所在，都可以从中学到很多东西。我过去觉得B端很没意思，但做了以后，发现B端也有它的魅力所在。无论做什么，都认真对待、都一视同仁，自能从中收获良多。如果因为平台和产品自己不喜欢，就不认真对待，那也不能指望别人还要尊重你的劳动吧？</p><h3 style=\"margin: 10px 0px; font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; line-height: 40px; color: rgb(51, 51, 51); text-rendering: optimizeLegibility; font-size: 24.5px; white-space: normal; widows: auto;\">四.善于总结</h3><p style=\"margin-top: 0px; margin-bottom: 1.5em; color: rgb(51, 51, 51); font-family: &#39;&quot;Microsoft YaHei&quot;&#39;, 微软雅黑, arial, sans-serif; font-size: 15px; line-height: 27px; white-space: normal; widows: auto;\">这一点同样感谢阿里，来这里后每周都需要写周报，而且字数要求还不低。在写周报的过程中，需要完整反思一下自己过去一周做了什么事情，收获是什么，不足是什么，应该如何提升，并且展示给组内的同事看，让大家明白你做的东西，而不是简单汇报一下项目名称、贴个设计稿链接就可以了事。在周报的影响下，我也慢慢形成了定期总结反思的习惯，以文字记录下来（比如你在看的简书），可以直接感受到自己价值的增长在哪里，潜力在哪里，不足在哪里，未来更针对性地为提升自己的价值而努力。</p><p><br/></p>', '/HomeCenterService/upload/images/20150702154023860101.jpg', '2015-07-02 15:40:42', '鸿影Akiko', '对设计与设计师“价值”的一些思考', '对设计与设计师“价值”的一些思考', '3', '设计，设计师，交互');

-- ----------------------------
-- Table structure for homework_menu
-- ----------------------------
DROP TABLE IF EXISTS `homework_menu`;
CREATE TABLE `homework_menu` (
  `menu_id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `menu_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单名字',
  `menu_parent_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_url` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '菜单链接',
  `menu_create_user` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_create_time` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_icon` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_menu
-- ----------------------------
INSERT INTO `homework_menu` VALUES ('0de179bc-bfc4-4450-a62f-457a6b8a0677', '日程安排', 'd29228b8-2d5f-4951-b928-5a2e7aff4642', '/user_schedule.html', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-list-alt', '11');
INSERT INTO `homework_menu` VALUES ('15f3785b-e45f-4f7e-96e8-72aa091b8172', '权限配置', 'a4c78339-cf93-4642-be1b-57b5dafb2ab6', '/system_authority', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-eye-open', '63');
INSERT INTO `homework_menu` VALUES ('405c5cca-b1e1-4283-b486-f7d53475cbea', '个人资料', 'd29228b8-2d5f-4951-b928-5a2e7aff4642', '/person_manage.html', 'admin', '2015-08-21 12:16:27', 'glyphicon glyphicon-user', '12');
INSERT INTO `homework_menu` VALUES ('45868e8a-e453-4e3d-979e-3415f859a04d', '网络图片', 'root', '#', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-picture', '5');
INSERT INTO `homework_menu` VALUES ('4c4d11ca-455b-4bd8-9ab9-630fe52ffeda', '文章一览', 'c2bd7195-32f7-47b0-96fc-827c8e0e0226', '/aticle_browser.html', 'admin', '2015-08-21 12:16:27', 'glyphicon glyphicon-list', '31');
INSERT INTO `homework_menu` VALUES ('54c729c3-9c73-4e32-872a-d645c7c31cf9', '音乐一览', 'e9992506-e269-4219-9116-9b81d9ca6544', '/music_view.html', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-list', '41');
INSERT INTO `homework_menu` VALUES ('675bf996-85e5-4c90-9956-cc089008559a', '新增文章', 'c2bd7195-32f7-47b0-96fc-827c8e0e0226', '/aticle_add.html', 'admin', '2015-08-21 12:16:27', 'glyphicon glyphicon-plus-sign', '32');
INSERT INTO `homework_menu` VALUES ('932f7fd0-7c9c-4f3c-ad84-65a806e68fb0', '增加模版', 'f95e66ee-05d8-4685-9971-bb93a3d94ce4', '/template_add.html', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-plus', '22');
INSERT INTO `homework_menu` VALUES ('a00abd9c-410f-4310-8262-7f50c209bfa0', '用户管理', 'a4c78339-cf93-4642-be1b-57b5dafb2ab6', '/system_user.html', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-user', '62');
INSERT INTO `homework_menu` VALUES ('a4c78339-cf93-4642-be1b-57b5dafb2ab6', '系统管理', 'root', '#', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-cog', '6');
INSERT INTO `homework_menu` VALUES ('c2bd7195-32f7-47b0-96fc-827c8e0e0226', '我的文章', 'root', '#', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-book', '3');
INSERT INTO `homework_menu` VALUES ('d29228b8-2d5f-4951-b928-5a2e7aff4642', '个人中心', 'root', '#', 'admin', '2015-07-26 12:16:27', 'fa fa-reddit-square', '1');
INSERT INTO `homework_menu` VALUES ('e9992506-e269-4219-9116-9b81d9ca6544', '网络音乐', 'root', '#', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-headphones', '4');
INSERT INTO `homework_menu` VALUES ('ea159abc-86c9-4d1f-8fa0-baca94a70cab', '转载文章', 'c2bd7195-32f7-47b0-96fc-827c8e0e0226', '/aticle_reprint.html', 'admin', '2015-08-21 12:16:27', 'glyphicon glyphicon-share-alt', '33');
INSERT INTO `homework_menu` VALUES ('f55d2ac6-03a3-4e65-b47c-0a4b72cbcf55', '模板一览', 'f95e66ee-05d8-4685-9971-bb93a3d94ce4', '/template_view.html', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-list', '21');
INSERT INTO `homework_menu` VALUES ('f95e66ee-05d8-4685-9971-bb93a3d94ce4', '网页模板', 'root', '#', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-th', '2');
INSERT INTO `homework_menu` VALUES ('fef50057-94b8-4e7e-b5be-077556943260', '菜单管理', 'a4c78339-cf93-4642-be1b-57b5dafb2ab6', '/system_menu.html', 'admin', '2015-07-26 12:16:27', 'glyphicon glyphicon-list-alt', '61');

-- ----------------------------
-- Table structure for homework_role
-- ----------------------------
DROP TABLE IF EXISTS `homework_role`;
CREATE TABLE `homework_role` (
  `role_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_description` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_role
-- ----------------------------

-- ----------------------------
-- Table structure for homework_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `homework_role_menu`;
CREATE TABLE `homework_role_menu` (
  `role_menu_id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `role_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `menu_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for homework_schedule
-- ----------------------------
DROP TABLE IF EXISTS `homework_schedule`;
CREATE TABLE `homework_schedule` (
  `schedule_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `schedule_content` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `schedule_start_date` datetime DEFAULT NULL,
  `schedule_end_date` datetime DEFAULT NULL,
  `schedule_user` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_schedule
-- ----------------------------
INSERT INTO `homework_schedule` VALUES ('91314108-a866-40a6-90d8-b71efdffba6b', 'haokaixin', '2015-08-05 18:02:31', '2015-08-05 23:56:49', 'admin');
INSERT INTO `homework_schedule` VALUES ('d9789dd6-cdd6-46b5-9d23-b5aed57e6702', 'haowuniao', '2015-08-05 18:02:47', '2015-08-13 09:56:51', 'admin');
INSERT INTO `homework_schedule` VALUES ('3c35a681-e81d-4c65-b45b-f5cf29d73d61', '好烦呀', '2015-08-03 18:03:07', '2015-08-04 09:56:56', 'admin');
INSERT INTO `homework_schedule` VALUES ('66532f23-160d-4ee3-95fc-6ec2b34e6367', '出去玩', '2015-08-05 16:28:58', '2015-08-05 16:28:58', 'adminjoker');
INSERT INTO `homework_schedule` VALUES ('6e5401cd-f140-422a-a89e-8bb6360a7286', '做页面', '2015-08-05 16:53:40', '2015-08-05 16:53:40', 'adminjoker');

-- ----------------------------
-- Table structure for homework_template
-- ----------------------------
DROP TABLE IF EXISTS `homework_template`;
CREATE TABLE `homework_template` (
  `template_id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `template_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `template_snapshot` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `templaye_browser_num` int(11) DEFAULT NULL,
  `template_describe` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '模板的描述',
  `template_tip` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '模板的标签，以，隔开，最多5个',
  `template_resource_url` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `template_uplodad_user` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `template_create_time` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `template_access_url` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`template_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_template
-- ----------------------------
INSERT INTO `homework_template` VALUES ('08623cf1-43ba-4bb0-9a84-618263f0facf', '测试模板3', 'upload\\images\\201508190412245015.jpg,', '0', '模板描述3', '测试', 'upload\\resources\\2015081904122499197.zip', 'colin', '2015-08-19 16:12:24', 'upload/template/2015081904122499197/index.html');
INSERT INTO `homework_template` VALUES ('6ed7a924-9c68-47bc-98b7-36f04ff5e015', 'fewagaer', 'upload\\images\\201507280430523872.jpg,upload\\images\\201507280430521407.jpg,', '0', 'h6ys4', 'j67jd', 'upload\\resources\\2015072804305239910.rar', 'adminjoker', '2015-07-28 04:30:52', 'upload\\template\\2015072804305239910.rarindex.html');
INSERT INTO `homework_template` VALUES ('777f1e8b-d450-4673-b939-89dbba867ebb', '测试模板2', 'upload\\images\\201508190409332426.jpg,', '0', '测试模板描述2', 'html5,2', 'upload\\resources\\2015081904093321489.zip', 'colin', '2015-08-19 16:09:33', 'upload\\template/2015081904093321489/index.html');
INSERT INTO `homework_template` VALUES ('7a035c11-4446-4357-83db-127822d1e3de', '测试模板', 'upload\\images\\201508190402337685.jpg,', '0', '测试模板描述', 'html5', 'upload\\resources\\2015081904023396126.zip', 'colin', '2015-08-19 04:02:34', 'upload\\template\\2015081904023396126\\index.html');
INSERT INTO `homework_template` VALUES ('868a32bb-c9c4-3d89-93af-a9c0b11cc162', 'cesdf', 'upload\\images\\20150728031212641.jpg,upload\\images\\201507280312127527.jpg,', '0', 'gaergser', 'gwe4', 'upload\\resources\\2015072803121273715.rar', '', '2015-07-28 03:12:12', 'upload\\template\\2015072803121273715.rarindex.html');
INSERT INTO `homework_template` VALUES ('868a32bb-c9c4-4b2349-93af-a9c0b11cc162', 'cesdf', 'upload\\images\\20150728031212641.jpg,upload\\images\\201507280312127527.jpg,', '0', 'gaergser', 'gwe4', 'upload\\resources\\2015072803121273715.rar', '', '2015-07-28 03:12:12', 'upload\\template\\2015072803121273715.rarindex.html');
INSERT INTO `homework_template` VALUES ('868a32bb-c9c4-4b89-93af-a9c0241cc162', 'cesdf', 'upload\\images\\20150728031212641.jpg,upload\\images\\201507280312127527.jpg,', '0', 'gaergser', 'gwe4', 'upload\\resources\\2015072803121273715.rar', '', '2015-07-28 03:12:12', 'upload\\template\\2015072803121273715.rarindex.html');
INSERT INTO `homework_template` VALUES ('868a32bb-c9c4-4b89-93af-a9c0b11cc146', 'cesdf', 'upload\\images\\20150728031212641.jpg,upload\\images\\201507280312127527.jpg,', '0', 'gaergser', 'gwe4', 'upload\\resources\\2015072803121273715.rar', '', '2015-07-28 03:12:12', 'upload\\template\\2015072803121273715.rarindex.html');
INSERT INTO `homework_template` VALUES ('868a32bb-c9c4-4b89-93af-a9c0b11cc162', 'cesdf', 'upload\\images\\20150728031212641.jpg,upload\\images\\201507280312127527.jpg,', '0', 'gaergser', 'gwe4', 'upload\\resources\\2015072803121273715.rar', '', '2015-07-28 03:12:12', 'upload\\template\\2015072803121273715.rarindex.html');
INSERT INTO `homework_template` VALUES ('868a32bb-c9c4-4b89-93af-a9c0b11cc165', 'cesdf', 'upload\\images\\20150728031212641.jpg,upload\\images\\201507280312127527.jpg,', '0', 'gaergser', 'gwe4', 'upload\\resources\\2015072803121273715.rar', '', '2015-07-28 03:12:12', 'upload\\template\\2015072803121273715.rarindex.html');
INSERT INTO `homework_template` VALUES ('868a32bb-c9c4-4b89-93af-a9c0b11cc32534', 'cesdf', 'upload\\images\\20150728031212641.jpg,upload\\images\\201507280312127527.jpg,', '0', 'gaergser', 'gwe4', 'upload\\resources\\2015072803121273715.rar', '', '2015-07-28 03:12:12', 'upload\\template\\2015072803121273715.rarindex.html');
INSERT INTO `homework_template` VALUES ('868a32bb-c9c4-4b89-93af-a9c0b11cc4562', 'cesdf', 'upload\\images\\20150728031212641.jpg,upload\\images\\201507280312127527.jpg,', '0', 'gaergser', 'gwe4', 'upload\\resources\\2015072803121273715.rar', '', '2015-07-28 03:12:12', 'upload\\template\\2015072803121273715.rarindex.html');
INSERT INTO `homework_template` VALUES ('8c69047a-9c2e-4ae8-b4b4-38757f1f806b', 'bhrjhdt', 'upload\\images\\20150728041405633.jpg,upload\\images\\201507280414053815.jpg,', '0', 'gsergts', 'hrths', 'upload\\resources\\201507280414056953.rar', 'adminjoker', '2015-07-28 04:14:05', 'upload\\template\\201507280414056953.rarindex.html');
INSERT INTO `homework_template` VALUES ('ae988cb1-ccc0-40ef-91ff-5ee152ce6702', '测试模板2', 'upload\\images\\201508190319445990.jpg,', '0', '测试内容描述', 'html5,模板', 'upload\\resources\\2015081903194451871.rar', 'colin', '2015-08-19 03:19:45', 'upload\\template\\2015081903194451871\\index.html');

-- ----------------------------
-- Table structure for homework_user
-- ----------------------------
DROP TABLE IF EXISTS `homework_user`;
CREATE TABLE `homework_user` (
  `user_id` int(40) NOT NULL,
  `user_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_password` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_role` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_callname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_email` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_user
-- ----------------------------
INSERT INTO `homework_user` VALUES ('1', 'adminjoker', 'admin111', null, 'joker', null);
INSERT INTO `homework_user` VALUES ('2', 'bbb', '1234', null, null, null);
INSERT INTO `homework_user` VALUES ('3', 'colin', 'admin123', null, 'colin', '929268411@qq.com');
INSERT INTO `homework_user` VALUES ('4', 'joker', 'admin123', null, 'joker', '929268411@qq.com');

-- ----------------------------
-- Table structure for homework_user_info
-- ----------------------------
DROP TABLE IF EXISTS `homework_user_info`;
CREATE TABLE `homework_user_info` (
  `user_info_id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `user_info_firstname` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户的姓',
  `user_info_lastname` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户的名',
  `user_info_province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户所在省',
  `user_info_city` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户所在市',
  `user_info_area` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户所在区',
  `user_info_birthday` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户生日',
  `user_info_address` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户地址',
  `user_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_user_info
-- ----------------------------

-- ----------------------------
-- Table structure for homework_user_role
-- ----------------------------
DROP TABLE IF EXISTS `homework_user_role`;
CREATE TABLE `homework_user_role` (
  `user_role_id` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role_id` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of homework_user_role
-- ----------------------------
