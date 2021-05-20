/* This file contains configuration settings for for all our platforms(posix). */

#ifndef IOT_CONFIG_H_
#define IOT_CONFIG_H_

/* Enable asserts in the libraries. */
#define IOT_CONTAINERS_ENABLE_ASSERTS           ( 1 )
#define IOT_MQTT_ENABLE_ASSERTS                 ( 1 )
#define IOT_TASKPOOL_ENABLE_ASSERTS             ( 1 )
#define AWS_IOT_SHADOW_ENABLE_ASSERTS           ( 1 )
#define AWS_IOT_DEFENDER_ENABLE_ASSERTS         ( 1 )
#define AWS_IOT_JOBS_ENABLE_ASSERTS             ( 1 )

/* Library logging configuration. IOT_LOG_LEVEL_GLOBAL provides a global log
 * level for all libraries; the library-specific settings override the global
 * setting. If both the library-specific and global settings are undefined,
 * no logs will be printed. */
#define IOT_LOG_LEVEL_GLOBAL                    IOT_LOG_DEBUG
//#define IOT_LOG_LEVEL_PLATFORM                  IOT_LOG_NONE
//#define IOT_LOG_LEVEL_NETWORK                   IOT_LOG_NONE
//#define IOT_LOG_LEVEL_TASKPOOL                  IOT_LOG_NONE
//#define IOT_LOG_LEVEL_MQTT                      IOT_LOG_INFO
//#define AWS_IOT_LOG_LEVEL_SHADOW                IOT_LOG_INFO
//#define AWS_IOT_LOG_LEVEL_DEFENDER              IOT_LOG_INFO
//#define AWS_IOT_LOG_LEVEL_JOBS                  IOT_LOG_INFO

/* Default assert and memory allocation functions. */
#include <assert.h>
#include <stdlib.h>

#define Iot_DefaultAssert    assert
#define Iot_DefaultMalloc    malloc
#define Iot_DefaultFree      free

/* The build system will choose the appropriate system types file for the platform
 * layer based on the host operating system. */

#ifdef IOT_SYSTEM_TYPES_FILE
#include IOT_SYSTEM_TYPES_FILE
#else
#include "types/iot_platform_types_posix.h"
#endif

#endif /* ifndef IOT_CONFIG_H_ */
