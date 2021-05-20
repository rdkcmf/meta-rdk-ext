##########################################################################
# If not stated otherwise in this file or this component's LICENSE
# file the following copyright and licenses apply:
#
# Copyright 2019 RDK Management
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
##########################################################################

cmake_minimum_required(VERSION 1.6.3)

set(THREADS_PTHREAD_ARG "2" CACHE STRING "Forcibly set by CMakeLists.txt." FORCE)

PROJECT(kvs)

IF(NOT DEFINED PROJECT_ROOT)
        GET_FILENAME_COMPONENT(PROJECT_ROOT "." ABSOLUTE)
ENDIF(NOT DEFINED PROJECT_ROOT)

GET_FILENAME_COMPONENT(KVS_NATIVE_BUILD_ROOT "${PROJECT_ROOT}/kinesis-video-native-build" ABSOLUTE)
GET_FILENAME_COMPONENT(KVS_VIDEO_PRODUCER_ROOT "${PROJECT_ROOT}/kinesis-video-producer" ABSOLUTE)
GET_FILENAME_COMPONENT(KVS_VIDEO_PIC_ROOT "${PROJECT_ROOT}/kinesis-video-pic" ABSOLUTE)
GET_FILENAME_COMPONENT(KVS_VIDEO_C_PRODUCER_ROOT "${PROJECT_ROOT}/kinesis-video-c-producer" ABSOLUTE)
#GET_FILENAME_COMPONENT(KVS_VIDEO_JNI_ROOT "${PROJECT_ROOT}/kinesis-video-producer-jni" ABSOLUTE)
GET_FILENAME_COMPONENT(KVS_VIDEO_GST_PLUGIN_ROOT "${PROJECT_ROOT}/kinesis-video-gstreamer-plugin" ABSOLUTE)

ADD_SUBDIRECTORY(${KVS_NATIVE_BUILD_ROOT})
ADD_SUBDIRECTORY(${KVS_VIDEO_PRODUCER_ROOT})
ADD_SUBDIRECTORY(${KVS_VIDEO_PIC_ROOT})
ADD_SUBDIRECTORY(${KVS_VIDEO_C_PRODUCER_ROOT})
#ADD_SUBDIRECTORY(${KVS_VIDEO_JNI_ROOT})
ADD_SUBDIRECTORY(${KVS_VIDEO_GST_PLUGIN_ROOT})
