cmake_minimum_required(VERSION 2.8)

add_library(duktape
            SHARED
            src/duktape.c
            extras/print-alert/duk_print_alert.c)

set_target_properties(duktape PROPERTIES VERSION 2.2.0)

target_include_directories(duktape
                           PUBLIC $<BUILD_INTERFACE:${CMAKE_SOURCE_DIR}/src>
                                  $<INSTALL_INTERFACE:include/duktape>
                           PRIVATE ${CMAKE_SOURCE_DIR}/extras/print-alert)

install(TARGETS duktape
        LIBRARY DESTINATION lib
        PUBLIC_HEADER DESTINATION include)
install(FILES
        ${CMAKE_SOURCE_DIR}/src/duktape.h
        ${CMAKE_SOURCE_DIR}/src/duk_config.h
        ${CMAKE_SOURCE_DIR}/extras/print-alert/duk_print_alert.h
        DESTINATION include/duktape)
