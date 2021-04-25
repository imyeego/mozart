cmake_minimum_required(VERSION 3.4.1)
#include_directories(src/main/cpp/libyuv/include)
#add_subdirectory(src/main/cpp/libyuv ./build)
# 查找指定目录下的所有源文件，然后将结果存进指定变量名
aux_source_directory(./ SRC_FILE)
add_library(ndktest
        SHARED
        ${SRC_FILE}
        )
find_library(log-lib log)
target_link_libraries(ndktest ${log-lib})