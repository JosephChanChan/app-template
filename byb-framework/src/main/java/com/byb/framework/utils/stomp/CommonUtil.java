package com.byb.framework.utils.stomp;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 *
 * @author liuanxin
 * @author Joseph
 * @date 2017/12/27
 */
public class CommonUtil {

    /**
     * 获取唯一ID（年+月+日+时+分+秒+三位数微秒+三位随机数，共20个字符）
     *
     * @return
     */
    public static String getUniqueID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timeStr = sdf.format(new Date());
        return timeStr + String.valueOf((int) (Math.random() * 900) + 100);
    }

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getUUid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取压缩的22位uuid
     *
     * @return
     */
    @SuppressWarnings("unused")
    public static String compressedUUID() {
        UUID uuid = UUID.randomUUID();
        byte[] byUuid = new byte[16];
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        long2bytes(most, byUuid, 0);
        long2bytes(least, byUuid, 8);
        String compressUUID = Base64.encodeBase64URLSafeString(byUuid);
        return compressUUID;
    }

    public static void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = 7; i > -1; --i) {
            bytes[offset++] = (byte) ((int) (value >> 8 * i & 255L));
        }
    }


    /**
     * 生成订单ID（E+年+月+日+时+分+秒+三位数微秒+三位随机数，共21个字符）
     *
     * @return
     */
    public static String getOrderId() {
        return "E" + getUniqueID();
    }

    public static final Charset UTF8 = StandardCharsets.UTF_8;
    public static final Random RANDOM = ThreadLocalRandom.current();

    /** 本机的 cpu 核心数 */
    public static final int PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String SPLIT = ",|，";

    private static final String LIKE = "%";
    private static final String PHONE = "^1[0-9]{10}$";
    /** _abc-def@123-hij.uvw_xyz.com 是正确的, -123@xyz.com 不是 */
    private static final String EMAIL = "^\\w[\\w\\-]*@([\\w\\-]+\\.\\w+)+$";
    /** ico, jpeg, jpg, bmp, png 后缀 */
    private static final String IMAGE = "(?i)^(.*)\\.(ico|jpeg|jpg|bmp|png)$";
    /** 帐号输入(字母或数字开头, 长度 5-30, 可以有下划线) */
    private static final String USER_NAME = "^[a-zA-Z0-9]\\w{4,29}$";
    /** IPv4 地址 */
    private static final String IPV4 = "^([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])(\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])){3}$";
    /** 身份证号码 */
    private static final String ID_CARD = "^([0-9]{15}|[0-9]{17}[0-9Xx])$";

    /** 中文 */
    private static final String CHINESE = "[\\u4e00-\\u9fa5]";
    /** 是否是移动端: https://gist.github.com/dalethedeveloper/1503252 */
    private static final String MOBILE = "(?i)Mobile|iP(hone|od|ad)|Android|BlackBerry|Blazer|PSP|UCWEB|IEMobile|Kindle|NetFront|Silk-Accelerated|(hpw|web)OS|Fennec|Minimo|Opera M(obi|ini)|Dol(f|ph)in|Skyfire|Zune";
    /** 是否是 iOS 端 */
    private static final String IOS = "(?i)iP(hone|od|ad)";
    /** 是否是 android 端 */
    private static final String ANDROID = "(?i)Mobile|Android";
    /** 是否是 pc 端 */
    private static final String PC = "(?i)AppleWebKit|Mozilla|Chrome|Safari|MSIE|Windows NT";
    /** 是否是本地 ip */
    private static final String LOCAL = "(?i)127.0.0.1|localhost|::1|0:0:0:0:0:0:0:1";

    /** 生成指定位数的随机数(数字) */
    public static String random(int length) {
        if (length <= 0) {
            return EMPTY;
        }

        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sbd.append(RANDOM.nextInt(10));
        }
        return sbd.toString();
    }

    private static final String TMP = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /** 生成指定位数的随机数(数字或字母) */
    public static String randomLetterAndNumber(int length) {
        if (length <= 0) {
            return EMPTY;
        }

        StringBuilder sbd = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sbd.append(TMP.charAt(RANDOM.nextInt(TMP.length())));
        }
        return sbd.toString();
    }

    /**
     * 获取枚举中的值, 先匹配 name, 再匹配 getCode(数字), 再匹配 getValue(中文), 都匹配不上则返回 null
     *
     * @param clazz 枚举的类信息
     * @param obj 要匹配的值
     */
    public static <E extends Enum> E toEnum(Class<E> clazz, Object obj) {
        if (isNotBlank(obj)) {
            E[] constants = clazz.getEnumConstants();
            if (constants != null) {
                String source = obj.toString().trim();
                for (E em : constants) {
                    // 如果传递过来的是枚举名, 且能匹配上则返回
                    if (source.equalsIgnoreCase(em.name())) {
                        return em;
                    }

                    // 如果传递过来的值跟枚举的 getCode(数字) 相同则返回
                    Object code = getMethod(em, "getCode");
                    if (code != null && source.equalsIgnoreCase(code.toString().trim())) {
                        return em;
                    }

                    // 如果传递过来的值跟枚举的 getValue(中文) 相同则返回
                    code = getMethod(em, "getValue");
                    if (code != null && source.equalsIgnoreCase(code.toString().trim())) {
                        return em;
                    }

                    // 如果传递过来的值跟枚举的 ordinal(数字. 表示枚举所在的索引) 相同则返回
                    // if (source.equalsIgnoreCase(String.valueOf(em.ordinal()))) {
                    //     return em;
                    // }
                }
            }
        }
        return null;
    }


    // ========== number ==========
    /** 传入的数不为 null 且 大于 0 就返回 true */
    public static boolean greater0(Number obj) {
        return obj != null && obj.doubleValue() > 0;
    }
    /** 传入的数不为 null 且 大于等于 0 就返回 true */
    public static boolean gte0(Number obj) {
        return obj != null && obj.doubleValue() >= 0;
    }
    /** 传入的数为 null 或 小于等于 0 就返回 true */
    public static boolean lesse0(Number obj) {
        return obj == null || obj.doubleValue() <= 0;
    }
    /** 传入的数为 null 或 小于 0 就返回 true */
    public static boolean less0(Number obj) {
        return obj == null || obj.doubleValue() < 0;
    }
    /** 数值在指定的数区间时(包含边界)返回 true */
    public static boolean betweenBorder(Number num, Number min, Number max) {
        return num.doubleValue() >= min.doubleValue() && num.doubleValue() <= max.doubleValue();
    }
    /** 数值不在指定的数区间时(包含边界)返回 true */
    public static boolean notBetweenBorder(Number num, Number min, Number max) {
        return !betweenBorder(num, min, max);
    }
    /** 数值在指定的数区间时(不包含边界)返回 true */
    public static boolean between(Number num, Number min, Number max) {
        return num.doubleValue() > min.doubleValue() && num.doubleValue() < max.doubleValue();
    }
    /** 数值不在指定的数区间时(不包含边界)返回 true */
    public static boolean notBetween(Number num, Number min, Number max) {
        return !between(num, min, max);
    }
    // ========== number ==========


    // ========== object & string ==========
    /** 对象为 null, 或者其字符串形态为 空白符, "null" 时返回 true */
    public static boolean isBlank(Object obj) {
        return obj == null || EMPTY.equals(obj.toString().trim()) || "null".equalsIgnoreCase(obj.toString().trim());
    }
    /** 对象非空时返回 true */
    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    /** 对象长度在指定的数值经内(包含边距)就返回 true */
    public static boolean lengthBorder(String str, int min, int max) {
        return !isBlank(str) && str.length() >= min && str.length() <= max;
    }
    /** 对象长度在指定的数值以内(不包含边距)就返回 true */
    public static boolean length(String str, int min, int max) {
        return lengthBorder(str, min + 1, max - 1);
    }

    /** 将字符串中指定位数的值模糊成 * 并返回. 索引位从 0 开始 */
    public static String foggy(String param, int start, int end) {
        if (isBlank(param)) {
            return EMPTY;
        }
        if (start < 0 || end < start || end > param.length()) {
            return param;
        }

        return param.substring(0, start) + param.substring(start, end).replaceAll("[0-9]", "*") + param.substring(end);
    }

    /** 去掉所有的空白符(空格, 制表符, 换行符) */
    public static String trim(String str) {
        return isBlank(str) ? EMPTY : str.replaceAll("\\s", "");
    }

    /** 获取图片后缀(包含点 .) */
    public static String getSuffix(String image) {
        return (isNotBlank(image) && image.contains("."))
                ? image.substring(image.lastIndexOf(".")) : EMPTY;
    }

    public static String like(String param) {
        return isBlank(param) ? CommonUtil.EMPTY : LIKE + param + LIKE;
    }
    public static String leftLike(String param) {
        return isBlank(param) ? CommonUtil.EMPTY : LIKE + param;
    }
    public static String rightLike(String param) {
        return isBlank(param) ? CommonUtil.EMPTY : param + LIKE;
    }
    // ========== object & string ==========


    // ========== regex ==========
    /**
     * 验证 指定正则 是否 <span style="color:red;">全字匹配</span> 指定字符串, 匹配则返回 true <br/><br/>
     *
     * 左右空白符 : (?m)(^\s*|\s*$)<br>
     * 空白符 : (^\\s*)|(\\s*$)<br/>
     * 匹配多行注释 : /\*\*(\s|.)*?\* /<br/>
     */
    public static boolean checkRegexWithStrict(String param, String regex) {
        return isNotBlank(param) && Pattern.compile(regex).matcher(param).matches();
    }
    /** 后缀是图片则返回 true */
    public static boolean checkImage(String image) {
        return checkRegexWithStrict(image, IMAGE);
    }
    /** 是正确的邮箱地址则返回 true */
    public static boolean checkEmail(String email) {
        return checkRegexWithStrict(email, EMAIL);
    }
    /** 是一个手机则返回 true. 可以使用 - 和空格. 131-1234-5678, 131 1234 5678 是正确的手机号, 131-1234 5678 不是 */
    public static boolean checkPhone(String phone) {
        return checkRegexWithStrict(phone, PHONE);
    }
    public static boolean checkUserName(String userName) {
        return checkRegexWithStrict(userName, USER_NAME);
    }
    /** 是一个有效的 ip 地址则返回 true */
    public static boolean isLicitIp(String ip) {
        return checkRegexWithStrict(ip, IPV4);
    }
    /** 是一个有效的身份证号就返回 true */
    public static boolean isIdCard(String num) {
        return checkRegexWithStrict(num, ID_CARD);
    }
    /** 是本地请求则返回 true */
    public static boolean isLocalRequest(String ip) {
        return checkRegexWithStrict(ip, LOCAL);
    }

    /** 只要找到匹配即返回 true */
    public static boolean checkRegexWithRelax(String param, String regex) {
        return isNotBlank(param) && Pattern.compile(regex).matcher(param).find();
    }
    /** 传入的参数只要包含中文就返回 true */
    public static boolean checkChinese(String param) {
        return checkRegexWithRelax(param, CHINESE);
    }
    /** 传入的参数只要来自移动端就返回 true */
    public static boolean checkMobile(String param) {
        return checkRegexWithRelax(param, MOBILE);
    }
    /** 传入的参数只要是 iOS 端就返回 true */
    public static boolean checkIos(String param) {
        return checkRegexWithRelax(param, IOS);
    }
    /** 传入的参数只要是 android 端就返回 true */
    public static boolean checkAndroid(String param) {
        return checkRegexWithRelax(param, ANDROID);
    }
    /** 传入的参数只要是 pc 端就返回 true */
    public static boolean checkPc(String param) {
        return checkRegexWithRelax(param, PC);
    }


    /** 将两个 int 合并成 long */
    public static long merge(int property, int value) {
        return ((long) property << 32) + (long) value;
    }
    /** 将 long 拆分两个 int */
    public static int[] parse(long pv) {
        return new int[] { (int) (pv >> 32), (int) pv };
    }


    /** 字符转义. 主要针对 url 传递给后台前的操作. 如 ? 转换为 %3F, = 转换为 %3D, & 转换为 %26 等 */
    public static String urlEncode(String url) {
        if (isBlank(url)) {
            return EMPTY;
        }
        try {
            // java 中的 encode 是把空格变成 +, 转义后需要将 + 替换成 %20
            return URLEncoder.encode(url, UTF8.displayName());//.replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }
    /** 字符反转义, 主要针对 url 传递到后台后的操作 */
    public static String urlDecode(String src) {
        if (isBlank(src)) {
            return EMPTY;
        }
        try {
            // java 中的 encode 是把空格变成 +, 反转义前需要将 %20 替换成 +
            return URLDecoder.decode(src/*.replaceAll("%20", "\\+")*/, UTF8.displayName());
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /** 生成不带 - 的 uuid */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    /** 将传入的文件重命名成不带 - 的 uuid 名称并返回 */
    public static String renameFile(String fileName) {
        return uuid() + getSuffix(fileName);
    }

    /** 为空则返回空字符串, 如果传入的 url 中有 ? 则在尾部拼接 &, 否则拼接 ? 返回 */
    public static String appendUrl(String src) {
        if (isBlank(src)) {
            return EMPTY;
        }
        return src + (src.contains("?") ? "&" : "?");
    }
    /** 为空则返回 /, 如果开头有 / 则直接返回, 否则在开头拼接 / 并返回 */
    public static String addPrefix(String src) {
        if (isBlank(src)) {
            return "/";
        }
        if (src.startsWith("/")) {
            return src;
        }
        return "/" + src;
    }
    /** 为空则返回 /, 如果结尾有 / 则直接返回, 否则在结尾拼接 / 并返回 */
    public static String addSuffix(String src) {
        if (isBlank(src)) {
            return "/";
        }
        if (src.endsWith("/")) {
            return src;
        }
        return src + "/";
    }
    /** 从 url 中获取图片名, 最后一个 斜杆(/) 后的内容 */
    public static String getFileNameInUrl(String url) {
        if (isBlank(url) || !url.contains("/")) {
            return EMPTY;
        }
        // 只截取到 ? 处, 如果有的话
        int last = url.contains("?") ? url.lastIndexOf("?") : url.length();
        return url.substring(url.lastIndexOf("/") + 1, last);
    }

    /** 当值为 null, 空白符, "null" 时则返回指定的字符 */
    public static String getNil(Object obj, String defaultStr) {
        return isBlank(obj) ? defaultStr : obj.toString().trim();
    }
    /** 当值为 null, 空白符, "null" 时, 返回空字符串 */
    public static String getNil(Object obj) {
        return getNil(obj, EMPTY);
    }

    /** 属性转换成方法, 加上 get 并首字母大写 */
    public static String fieldToMethod(String field) {
        if (isBlank(field)) {
            return EMPTY;
        }

        field = field.trim();
        return  "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    /** 调用对象的公有方法. 将会忽略异常只返回 null, 如果要对异常专门记录勿调用此方法 */
    public static Object getMethod(Object obj, String method, Object... param) {
        if (isNotBlank(method)) {
            try {
                return obj.getClass().getDeclaredMethod(method).invoke(obj, param);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                // ignore
            }
            // getMethod 会将从父类继承过来的 public 方法也查询出来
            try {
                return obj.getClass().getMethod(method).invoke(obj, param);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
                // ignore
            }
        }
        return null;
    }

    /** 字符串都是数字*/
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    /** 字符串都是字母*/
    public static boolean judgeContainsStr(String cardNum) {
        String regex=".*[a-zA-Z]+.*";
        Matcher m=Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }
    /**数字和字母*/
    public static boolean isNUmeAndStr(String str){
        String regex = "([A-Z]|[a-z]|[0-9]){0,}";
        return str.matches(regex);
    }
    public static String lastStr(String str){
        return str.substring(0,str.length()-1);
    }

    /** 比较 2 个字符串是否相等。可处理其中一个为 null 的情况 */
    public static boolean stringOf2Equals (String str1, String str2) {
        if (null == str1 && null == str2) {
            throw new RuntimeException(" Both str1 and str2 are null !");
        }
        if (null == str1) {
            return true;
        }
        if (null == str2) {
            return true;
        }
        return str1.equals(str2);
    }

    /**
     * 生成一个随机数，基于当前的时间（毫秒级别，格林威治时间自 1970-01-01 到现在的总秒数）加上一个 6 位长的伪随机数进一步扰乱.
     * 理论上冲突的概率为：在同 1 毫秒下 2 个请求以上调用此方法，同时产生的 6 位长度的伪随机数一致。
     * 即 1 毫秒内一百万个请求就有可能冲突。
     * Note:
     * 不适用于分布式高并发场景，10个节点在1毫秒内请求，相当于将冲突概率提高了10倍变成十万分一。
     */
    public static long generateRandomBaseMillisTime () {
        return Long.parseLong(System.currentTimeMillis() + random(6));
    }















    // ---------------------------------------------------------------------------------------------------------------------------------------------------------

    /*public static void main(String[] args) {
        long id = generateRandomBaseMillisTime();
        System.out.println(id);

        // 1559265819102454536
        // 9223372036854775807

        Set<Long> set = new HashSet<>();
        for (int i = 0; i < 99; i++) {
            long timeMillis = System.currentTimeMillis();
            System.out.println(timeMillis);
            set.add(timeMillis);
        }

        set.forEach(timeMillis -> {
            Date time = new Date(timeMillis);
            String format = DateKit.format(time, DateFormatType.YYYY_MM_DD_HH_MM_SS_SSS);
            System.out.println(format);
        });
    }*/
}
