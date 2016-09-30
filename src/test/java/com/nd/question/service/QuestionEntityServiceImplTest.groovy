package com.nd.question.service

import org.apache.commons.collections4.map.HashedMap
import org.junit.Test

import java.util.regex.MatchResult
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by ${zhiqiangXu}on 2016/9/12 0012.
 */
class QuestionEntityServiceImplTest {
    @Test
    void testUploadQuestions() {

    }

    @Test
    void test_1() {
        private final static Pattern CODE_PATTERN = Pattern.compile("【A-Z】", Pattern.MULTILINE);
        String code = "【A】 它是白色\n" +
                "    【B】 它是蓝色\n" +
                "    【C】 它是红色\n" +
                "    【D】 它是灰色";
        Map<String, String> codeMap = new HashedMap<>();
        List<MatchResult> list = new ArrayList<>();
        Matcher m = CODE_PATTERN.matcher(code);
        while (m.find()) {
            list.add(m.toMatchResult());
        }
        for (int i = 0; i < list.size(); i++) {
            String strTemp = "";
            MatchResult matcher = list.get(i);
            String index = matcher.group(1);
            int nextIdx = i + 1;
            if (list.size() == nextIdx) {
                strTemp = code.substring(matcher.end(), code.length());
                index = index.trim();
                strTemp = strTemp.trim();
                codeMap.put(index, strTemp);
            } else {
                MatchResult nextMatcher = list.get(nextIdx);
                strTemp = code.substring(matcher.end(), nextMatcher.start());
                index = index.trim();
                strTemp = strTemp.trim();
                codeMap.put(index, strTemp);
            }
            if (StringUtils.isEmpty(strTemp)) {
//                throw new FepException(FepErrorCodes.INVALID_TAGS);
            }
        }

        for(Map.Entry entry:codeMap){

        }

    }
}
